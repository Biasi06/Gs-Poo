package br.com.territorio.dominio;

import java.util.Random;
public class SensorChuva extends SensorSimulado {
    public SensorChuva(String id, String regiao, Random random) { super(id, "Pluviômetro", regiao, random); }
    @Override public TipoSensor retornarTipo() { return TipoSensor.CHUVA; }
    @Override protected double tetoSimulacao() { return 90; }
}
