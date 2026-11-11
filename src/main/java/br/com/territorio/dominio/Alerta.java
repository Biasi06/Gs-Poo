package br.com.territorio.dominio;

import java.time.LocalDateTime;
import br.com.territorio.excecao.DadosInvalidosException;
public class Alerta {
    private final String sensorId;
    private final TipoSensor tipo;
    private final NivelAlerta nivel;
    private final double valorPrevisto;
    private final double limite;
    private final String estrategia;
    private final String mensagem;
    private final LocalDateTime instante;
    public Alerta(String sensorId, TipoSensor tipo, NivelAlerta nivel, double valorPrevisto, double limite, String estrategia, String mensagem, LocalDateTime instante) {
        sensorId = Validacao.texto(sensorId, "Sensor");
        estrategia = Validacao.texto(estrategia, "Estratégia");
        mensagem = Validacao.texto(mensagem, "Mensagem");
        if (tipo == null || nivel == null || instante == null) throw new DadosInvalidosException("Alerta incompleto.");
        tipo.validar(valorPrevisto); tipo.validar(limite);
        if (limite <= 0 || valorPrevisto < limite) throw new DadosInvalidosException("Alerta exige limite positivo atingido.");
        this.sensorId = sensorId;
        this.tipo = tipo;
        this.nivel = nivel;
        this.valorPrevisto = valorPrevisto;
        this.limite = limite;
        this.estrategia = estrategia;
        this.mensagem = mensagem;
        this.instante = instante;
    }

    public String getSensorId() { return sensorId; }
    public TipoSensor getTipo() { return tipo; }
    public NivelAlerta getNivel() { return nivel; }
    public double getValorPrevisto() { return valorPrevisto; }
    public double getLimite() { return limite; }
    public String getEstrategia() { return estrategia; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getInstante() { return instante; }
}
