package com.example.vitorio.exemplominicursoenuncomp2017.modelo;

/**
 * Created by ivan on 25/10/17.
 */

public class Ponto {

    private String nome;
    private double latitude, longitude;

    public Ponto(){}
    public Ponto(String nome, double latitude, double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
