package com.tesis.alejofila.centrocomercial.model;

/**
 * Created by alejofila on 8/09/15.
 */
public class  Interes {
    private String channel;
    private int icono1;
    private int icono2;
    private boolean activado;

    public Interes(){}

    public Interes(String channel, int icono1, int icono2){
        this.channel = channel;
        this.icono1 = icono1;
        this.icono2 = icono2;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getIcono1() {
        return icono1;
    }

    public void setIcono1(int icono1) {
        this.icono1 = icono1;
    }

    public int getIcono2() {
        return icono2;
    }

    public void setIcono2(int icono2) {
        this.icono2 = icono2;
    }

    public boolean isActivado() {
        return activado;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }
}
