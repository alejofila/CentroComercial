package com.tesis.alejofila.centrocomercial.db;

/**
 * Created by Alejandro on 08/10/2015.
 */
public class TablesDB {

    public class Oferta {
        public static final String TABLE_NAME ="oferta";
        public static final String CN_ID_PRODUCTO="id_producto";
        public static final String CN_FECHA_INICIO="fecha_inicio";
        public static final String CN_FECHA_FINAL="fecha_final";
        public static final String CN_DESCRIPCION="descripcion";
        public static final String CN_TIENDA = "tienda";

        public static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+
                "("+CN_ID_PRODUCTO+" INTEGER NOT NULL,"
                +CN_FECHA_INICIO+ " DATE NOT NULL,"
                +CN_FECHA_FINAL+ " DATE NOT NULL,"
                +CN_DESCRIPCION+ " TEXT NOT NULL,"
                +CN_TIENDA+ " TEXT NOT NULL"
                +");";

    }

    public class Producto {
        public static final String TABLE_NAME ="producto";
        public static final String CN_ID ="id";
        public static final String CN_NOMBRE = "nombre";
        public static final String CN_PRECIO = "precio";
        public static final String CN_IMAGEN = "imagen";


        public static final String CREATE_TABLE ="CREATE TABLE "+TABLE_NAME+
                "("+CN_ID+" INTEGER PRIMARY KEY,"
                +CN_NOMBRE+ " TEXT NOT NULL ,"
                +CN_PRECIO+ " TEXT NOT NULL ,"
                +CN_IMAGEN+ " TEXT NOT NULL"
                +");";


    }
}
