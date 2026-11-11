package br.com.territorio.excecao;

public class FonteIndisponivelException extends RuntimeException {
    public FonteIndisponivelException(String mensagem) { super(mensagem); }
    public FonteIndisponivelException(String mensagem, Throwable causa) { super(mensagem, causa); }
}
