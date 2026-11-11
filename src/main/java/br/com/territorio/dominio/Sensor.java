package br.com.territorio.dominio;

public interface Sensor {
    String getId();
    String getNome();
    double lerValor();
    boolean verificarFuncionamento();
    TipoSensor retornarTipo();
}
