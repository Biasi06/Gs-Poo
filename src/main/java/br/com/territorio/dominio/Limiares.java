package br.com.territorio.dominio;
import br.com.territorio.excecao.DadosInvalidosException;

public class Limiares {
    private final double atencao;
    private final double alerta;
    private final double critico;

    public Limiares(double atencao, double alerta, double critico) {
        if (!Double.isFinite(atencao) || !Double.isFinite(alerta) || !Double.isFinite(critico)
                || atencao <= 0 || atencao >= alerta || alerta >= critico) {
            throw new DadosInvalidosException("Informe 0 < atenção < alerta < crítico, com valores finitos.");
        }
        this.atencao = atencao;
        this.alerta = alerta;
        this.critico = critico;
    }
    // null representa a ausência de alerta.
    public NivelAlerta classificar(double valor) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new DadosInvalidosException("Valor previsto inválido.");
        }
        if (valor >= critico) return NivelAlerta.CRITICO;
        if (valor >= alerta) return NivelAlerta.ALERTA;
        if (valor >= atencao) return NivelAlerta.ATENCAO;
        return null;
    }
    public double limite(NivelAlerta nivel) {
        switch (nivel) {
            case ATENCAO: return atencao;
            case ALERTA: return alerta;
            case CRITICO: return critico;
            default: throw new DadosInvalidosException("Nível desconhecido.");
        }
    }
    public double getAtencao() { return atencao; }
    public double getAlerta() { return alerta; }
    public double getCritico() { return critico; }
}
