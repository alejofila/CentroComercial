package com.tesis.alejofila.centrocomercial.model;


/**
 * Created by Alejandro on 18/10/2015.
 */
public class Producto
 {


    public Producto(){

    }

    public Producto(int id,String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    private  int id;
    private  String nombre;
    private  float precio;
    private String ruta_imagen;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

     public String getRuta_imagen() {
         return ruta_imagen;
     }

     public void setRuta_imagen(String ruta_imagen) {
         this.ruta_imagen = ruta_imagen;
     }
 }