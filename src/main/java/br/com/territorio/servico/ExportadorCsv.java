package br.com.territorio.servico;

import br.com.territorio.dominio.Medicao;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class ExportadorCsv {
    public void exportar(List<Medicao> historico, Path destino) throws IOException {
        if (destino.toAbsolutePath().getParent() != null) Files.createDirectories(destino.toAbsolutePath().getParent());
        try (BufferedWriter out = Files.newBufferedWriter(destino, StandardCharsets.UTF_8)) {
            out.write("sensor,tipo,valor,unidade,instante"); out.newLine();
            for (Medicao m : historico) {
                out.write(celula(m.getSensorId()) + "," + m.getTipo().name() + "," + m.getValor() + "," + celula(m.getTipo().getUnidade()) + "," + m.getInstante());
                out.newLine();
            }
        }
    }
    private String celula(String texto) { return "\"" + texto.replace("\"", "\"\"") + "\""; }
}
