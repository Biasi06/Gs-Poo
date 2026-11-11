package br.com.territorio.dominio;

import java.util.Random;
public class SensorUmidadeSolo extends SensorSimulado {
    public SensorUmidadeSolo(String id, String regiao, Random random) { super(id, "Umidade do solo", regiao, random); }
    @Override public TipoSensor retornarTipo() { return TipoSensor.UMIDADE_SOLO; }
    @Override protected double tetoSimulacao() { return 100; }
}
