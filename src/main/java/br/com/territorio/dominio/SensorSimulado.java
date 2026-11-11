package br.com.territorio.dominio;

import br.com.territorio.excecao.FonteIndisponivelException;
import java.util.Random;
import java.util.Objects;
public abstract class SensorSimulado extends ComponenteTerritorial implements Sensor {
    private final Random random;
    protected SensorSimulado(String id, String nome, String regiao, Random random) {
        super(id, nome, regiao); this.random = Objects.requireNonNull(random);
    }
    @Override public boolean verificarStatus() { return isAtivo(); }
    @Override public boolean verificarFuncionamento() { return verificarStatus(); }
    @Override public double lerValor() {
        if (!verificarFuncionamento()) throw new FonteIndisponivelException("Sensor " + getId() + " está indisponível.");
        double fracao;
        try { fracao = random.nextDouble(); }
        catch (RuntimeException e) { throw new FonteIndisponivelException("Falha no gerador do sensor " + getId(), e); }
        return retornarTipo().validar(Math.round(fracao * tetoSimulacao() * 100.0) / 100.0);
    }
    protected abstract double tetoSimulacao();
}
