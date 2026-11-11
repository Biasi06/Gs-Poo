package br.com.territorio.persistencia;

import br.com.territorio.dominio.*;
import java.sql.*;
import java.util.*;
public class AlertaDAO {
    private final BancoDados banco;
    public AlertaDAO(BancoDados banco) { this.banco = banco; }
    public void salvar(Alerta a) throws SQLException {
        try (Connection c = banco.abrirConexao(); PreparedStatement p = c.prepareStatement(
                "INSERT INTO alerta(sensor_id,tipo,nivel,valor_previsto,limite,estrategia,mensagem,instante) VALUES (?,?,?,?,?,?,?,?)")) {
            p.setString(1,a.getSensorId()); p.setString(2,a.getTipo().name()); p.setString(3,a.getNivel().name());
            p.setDouble(4,a.getValorPrevisto()); p.setDouble(5,a.getLimite()); p.setString(6,a.getEstrategia());
            p.setString(7,a.getMensagem()); p.setTimestamp(8,Timestamp.valueOf(a.getInstante())); p.executeUpdate();
        }
    }
    public List<Alerta> listarTodos() throws SQLException {
        try (Connection c = banco.abrirConexao(); PreparedStatement p = c.prepareStatement("SELECT * FROM alerta ORDER BY instante, id");
             ResultSet r = p.executeQuery()) {
            List<Alerta> lista = new ArrayList<>();
            while (r.next()) lista.add(new Alerta(r.getString("sensor_id"),TipoSensor.valueOf(r.getString("tipo")),
                    NivelAlerta.valueOf(r.getString("nivel")),r.getDouble("valor_previsto"),r.getDouble("limite"),
                    r.getString("estrategia"),r.getString("mensagem"),r.getTimestamp("instante").toLocalDateTime()));
            return lista;
        }
    }
}
