package br.com.territorio.dominio;

import java.time.LocalDateTime;
import br.com.territorio.excecao.DadosInvalidosException;
public class Medicao {
    private final String sensorId;
    private final TipoSensor tipo;
    private final double valor;
    private final LocalDateTime instante;
    public Medicao(String sensorId, TipoSensor tipo, double valor, LocalDateTime instante) {
        sensorId = Validacao.texto(sensorId, "Sensor");
        if (tipo == null || instante == null) throw new DadosInvalidosException("Medição exige tipo e instante.");
        tipo.validar(valor);
        this.sensorId = sensorId;
        this.tipo = tipo;
        this.valor = valor;
        this.instante = instante;
    }

    public String getSensorId() { return sensorId; }
    public TipoSensor getTipo() { return tipo; }
    public double getValor() { return valor; }
    public LocalDateTime getInstante() { return instante; }
}
