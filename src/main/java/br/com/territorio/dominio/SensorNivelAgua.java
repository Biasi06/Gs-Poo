package br.com.territorio.dominio;

import java.util.Random;
public class SensorNivelAgua extends SensorSimulado {
    public SensorNivelAgua(String id, String regiao, Random random) { super(id, "Régua do canal", regiao, random); }
    @Override public TipoSensor retornarTipo() { return TipoSensor.NIVEL_AGUA; }
    @Override protected double tetoSimulacao() { return 450; }
}
