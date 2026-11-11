package br.com.territorio.dominio;

public abstract class ComponenteTerritorial {
    private final String id, nome, regiao;
    private boolean status = true;
    protected ComponenteTerritorial(String id, String nome, String regiao) {
        this.id = Validacao.texto(id, "ID"); this.nome = Validacao.texto(nome, "Nome");
        this.regiao = Validacao.texto(regiao, "Região");
    }
    public abstract boolean verificarStatus();
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getRegiao() { return regiao; }
    public boolean isAtivo() { return status; }
    public void setAtivo(boolean ativo) { this.status = ativo; }
}
