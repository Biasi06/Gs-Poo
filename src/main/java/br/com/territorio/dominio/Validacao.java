package br.com.territorio.dominio;

import br.com.territorio.excecao.DadosInvalidosException;
public final class Validacao {
    private Validacao() {}
    public static String texto(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new DadosInvalidosException(campo + " não pode ficar vazio.");
        return valor.trim();
    }
    public static double faixa(double valor, double minimo, double maximo, String campo) {
        if (!Double.isFinite(valor) || valor < minimo || valor > maximo)
            throw new DadosInvalidosException(campo + " deve estar entre " + minimo + " e " + maximo + ".");
        return valor;
    }
}
