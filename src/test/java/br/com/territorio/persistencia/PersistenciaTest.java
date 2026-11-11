package br.com.territorio.persistencia;
import br.com.territorio.dominio.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PersistenciaTest {
    @TempDir Path pasta;

    @Test void medicoesEAlertasSobrevivemAReabertura() throws SQLException {
        // Arrange
        String url = "jdbc:h2:file:" + pasta.resolve("historico").toAbsolutePath();
        BancoDados banco = new BancoDados(url);
        banco.inicializar();
        LocalDateTime instante = LocalDateTime.of(2026,1,1,12,0);
        new MedicaoDAO(banco).salvar(new Medicao("CH-01",TipoSensor.CHUVA,70,instante));
        new AlertaDAO(banco).salvar(new Alerta("CH-01",TipoSensor.CHUVA,NivelAlerta.CRITICO,70,60,"Média","Limite atingido",instante));
        // Act
        BancoDados reaberto = new BancoDados(url);
        reaberto.inicializar();
        List<Medicao> medicoes = new MedicaoDAO(reaberto).buscarHistoricoPorSensor("CH-01");
        List<Alerta> alertas = new AlertaDAO(reaberto).listarTodos();
        // Assert
        assertEquals(1,medicoes.size());
        assertEquals(70,medicoes.get(0).getValor(),0.001);
        assertEquals(instante,medicoes.get(0).getInstante());
        assertEquals(1,alertas.size());
        assertEquals(NivelAlerta.CRITICO,alertas.get(0).getNivel());
        assertEquals(60,alertas.get(0).getLimite(),0.001);
    }
    @Test void historicoFiltraSensorEOrdenaLeituras() throws SQLException {
        // Arrange
        BancoDados banco = new BancoDados("jdbc:h2:file:" + pasta.resolve("ordem").toAbsolutePath());
        banco.inicializar();
        MedicaoDAO dao = new MedicaoDAO(banco);
        LocalDateTime instante = LocalDateTime.of(2026,1,1,12,0);
        dao.salvar(new Medicao("CH-01",TipoSensor.CHUVA,20,instante.plusHours(1)));
        dao.salvar(new Medicao("OUTRO",TipoSensor.CHUVA,99,instante));
        dao.salvar(new Medicao("CH-01",TipoSensor.CHUVA,10,instante));
        // Act
        List<Medicao> resultado = dao.buscarHistoricoPorSensor("CH-01");
        // Assert
        assertEquals(2,resultado.size());
        assertEquals(10,resultado.get(0).getValor(),0.001);
        assertEquals(20,resultado.get(1).getValor(),0.001);
        assertTrue(dao.buscarHistoricoPorSensor("' OR '1'='1").isEmpty());
    }
}
