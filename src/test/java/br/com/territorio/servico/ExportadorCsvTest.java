package br.com.territorio.servico;

import br.com.territorio.dominio.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
class ExportadorCsvTest {
    @TempDir Path pasta;
    @Test void exportaCabecalhoEValores() throws Exception {
        // Arrange
        Path arquivo = pasta.resolve("historico.csv");
        Medicao m = new Medicao("CH-01",TipoSensor.CHUVA,12.5,LocalDateTime.of(2026,1,1,12,0));
        // Act
        List<Medicao> medicoes = new ArrayList<>();
        medicoes.add(m);
        new ExportadorCsv().exportar(medicoes,arquivo);
        // Assert
        List<String> linhas = Files.readAllLines(arquivo);
        assertEquals("sensor,tipo,valor,unidade,instante",linhas.get(0));
        assertEquals("\"CH-01\",CHUVA,12.5,\"mm/h\",2026-01-01T12:00",linhas.get(1));
        assertEquals(2,linhas.size());
    }
}
