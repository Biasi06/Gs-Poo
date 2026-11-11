package br.com.territorio.persistencia;

import br.com.territorio.dominio.*;
import java.sql.*;
import java.util.*;
public class MedicaoDAO {
    private final BancoDados banco;
    public MedicaoDAO(BancoDados banco) { this.banco = banco; }
    public void salvar(Medicao m) throws SQLException {
        try (Connection c = banco.abrirConexao(); PreparedStatement p = c.prepareStatement(
                "INSERT INTO medicao(sensor_id, tipo, valor, instante) VALUES (?, ?, ?, ?)")) {
            p.setString(1, m.getSensorId()); p.setString(2, m.getTipo().name()); p.setDouble(3, m.getValor());
            p.setTimestamp(4, Timestamp.valueOf(m.getInstante())); p.executeUpdate();
        }
    }
    public List<Medicao> buscarHistoricoPorSensor(String sensorId) throws SQLException {
        try (Connection c = banco.abrirConexao(); PreparedStatement p = c.prepareStatement(
                "SELECT sensor_id, tipo, valor, instante FROM medicao WHERE sensor_id = ? ORDER BY instante, id")) {
            p.setString(1, sensorId);
            try (ResultSet r = p.executeQuery()) {
                List<Medicao> lista = new ArrayList<>();
                while (r.next()) lista.add(new Medicao(r.getString("sensor_id"), TipoSensor.valueOf(r.getString("tipo")),
                        r.getDouble("valor"), r.getTimestamp("instante").toLocalDateTime()));
                return lista;
            }
        }
    }
}
