package br.com.territorio.dominio;

import br.com.territorio.excecao.DadosInvalidosException;
import java.util.HashMap;
import java.util.Map;
public class DadosTerritorio {
    private final String regiao;
    private int populacao;
    private final String codigoAcesso;
    private final Map<TipoSensor, Limiares> limiares = new HashMap<>();
    public DadosTerritorio(String regiao, int populacao, String codigoAcesso) {
        this.regiao = Validacao.texto(regiao, "Região");
        this.codigoAcesso = Validacao.texto(codigoAcesso, "Código de acesso");
        setPopulacao(populacao);
        limiares.put(TipoSensor.CHUVA, new Limiares(20, 40, 60));
        limiares.put(TipoSensor.NIVEL_AGUA, new Limiares(150, 250, 350));
        limiares.put(TipoSensor.UMIDADE_SOLO, new Limiares(70, 85, 95));
    }
    public void setPopulacao(int populacao) {
        if (populacao < 0) throw new DadosInvalidosException("População não pode ser negativa.");
        this.populacao = populacao;
    }
    public void alterarLimiares(TipoSensor tipo, Limiares novos, String codigo) {
        if (!codigoAcesso.equals(codigo)) throw new DadosInvalidosException("Código de acesso incorreto. Limiares preservados.");
        if (tipo == null || novos == null) throw new DadosInvalidosException("Informe tipo e limiares.");
        tipo.validar(novos.getCritico());
        limiares.put(tipo, novos);
    }
    public String getRegiao() { return regiao; }
    public int getPopulacao() { return populacao; }
    public Limiares getLimiares(TipoSensor tipo) { return limiares.get(tipo); }
}
