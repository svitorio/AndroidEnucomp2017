package com.example.vitorio.exemplominicursoenuncomp2017.modelo;

/**
 * Created by ivan on 25/10/17.
 */

public class Ponto {

    private String nome;
    private float latitude, longitude;

    public Ponto(){}
    public Ponto(String nome, float latitude, float longitude) {
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

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
