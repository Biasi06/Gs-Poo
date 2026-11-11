package br.com.territorio.dominio;

public enum TipoSensor {
    CHUVA("Chuva acumulada na última hora", "mm/h", 200),
    NIVEL_AGUA("Nível da água no canal", "cm", 500),
    UMIDADE_SOLO("Umidade do solo", "%", 100);
    private final String descricao, unidade;
    private final double maximo;
    TipoSensor(String descricao, String unidade, double maximo) {
        this.descricao = descricao; this.unidade = unidade; this.maximo = maximo;
    }
    public String getDescricao() { return descricao; }
    public String getUnidade() { return unidade; }
    public double getMaximo() { return maximo; }
    public double validar(double valor) { return Validacao.faixa(valor, 0, maximo, descricao); }
}
