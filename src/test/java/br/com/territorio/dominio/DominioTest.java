package br.com.territorio.dominio;
import br.com.territorio.excecao.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class DominioTest {
    @Test void rejeitaMedicaoNegativa() {
        // Arrange
        LocalDateTime instante = LocalDateTime.now();
        // Act / Assert: lambda necessária à chamada assertThrows do JUnit.
        assertThrows(DadosInvalidosException.class, () -> new Medicao("CH-01", TipoSensor.CHUVA, -1, instante));
    }
    @Test void rejeitaMedicaoForaDaFaixa() {
        // Arrange
        LocalDateTime instante = LocalDateTime.now();
        // Act / Assert
        assertThrows(DadosInvalidosException.class, () -> new Medicao("US-01", TipoSensor.UMIDADE_SOLO, 101, instante));
        assertThrows(DadosInvalidosException.class, () -> new Medicao("US-01", TipoSensor.UMIDADE_SOLO, Double.NaN, instante));
    }
    @Test void populacaoNegativaPreservaValorAnterior() {
        // Arrange
        DadosTerritorio territorio = new DadosTerritorio("Bacia", 100, "senha");
        // Act / Assert
        assertThrows(DadosInvalidosException.class, () -> territorio.setPopulacao(-1));
        assertEquals(100, territorio.getPopulacao());
    }
    @Test void codigoIncorretoPreservaLimiares() {
        // Arrange
        DadosTerritorio territorio = new DadosTerritorio("Bacia", 100, "senha");
        // Act / Assert
        assertThrows(DadosInvalidosException.class, () -> territorio.alterarLimiares(TipoSensor.CHUVA, new Limiares(10,20,30), "errada"));
        assertEquals(20, territorio.getLimiares(TipoSensor.CHUVA).getAtencao(), 0.001);
    }
    @Test void codigoCorretoAlteraLimiares() {
        // Arrange
        DadosTerritorio territorio = new DadosTerritorio("Bacia", 100, "senha");
        // Act
        territorio.alterarLimiares(TipoSensor.CHUVA, new Limiares(10,20,30), "senha");
        // Assert
        assertEquals(10, territorio.getLimiares(TipoSensor.CHUVA).getAtencao(), 0.001);
    }
    @Test void rejeitaLimiaresDesordenados() {
        // Arrange
        double atencao = 30;
        double alerta = 20;
        // Act / Assert
        assertThrows(DadosInvalidosException.class, () -> new Limiares(atencao, alerta, 60));
    }
    @Test void classificaOsTresNiveis() {
        // Arrange
        Limiares limites = new Limiares(20,40,60);
        // Act / Assert
        assertNull(limites.classificar(19));
        assertEquals(NivelAlerta.ATENCAO, limites.classificar(20));
        assertEquals(NivelAlerta.ALERTA, limites.classificar(40));
        assertEquals(NivelAlerta.CRITICO, limites.classificar(60));
    }
    @Test void sensorInativoLancaExcecaoDaFonte() {
        // Arrange
        SensorChuva sensor = new SensorChuva("CH-01", "Bacia", new Random(1));
        sensor.setAtivo(false);
        // Act / Assert
        assertThrows(FonteIndisponivelException.class, () -> sensor.lerValor());
    }
}
