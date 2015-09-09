package com.tesis.alejofila.centrocomercial.model;

/**
 * Created by alejofila on 8/09/15.
 */
public class InteresRopaMasculina extends Interes{



    public InteresRopaMasculina(){
        setChannel("ropa_masculina");
        setIcono1(0x0);
        setIcono2(0x2);
    }

    @Override
    public String getChannel() {
        return super.getChannel();
    }

    @Override
    public void setChannel(String channel) {
        super.setChannel(channel);
    }

    @Override
    public int getIcono1() {
        return super.getIcono1();
    }

    @Override
    public void setIcono1(int icono1) {
        super.setIcono1(icono1);
    }

    @Override
    public int getIcono2() {
        return super.getIcono2();
    }

    @Override
    public void setIcono2(int icono2) {
        super.setIcono2(icono2);
    }

    @Override
    public boolean isActivado() {
        return super.isActivado();
    }

    @Override
    public void setActivado(boolean activado) {
        super.setActivado(activado);
    }
}
