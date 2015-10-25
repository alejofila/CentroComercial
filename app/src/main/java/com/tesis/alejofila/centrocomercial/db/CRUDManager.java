package com.tesis.alejofila.centrocomercial.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tesis.alejofila.centrocomercial.model.Oferta;
import com.tesis.alejofila.centrocomercial.model.Producto;
import com.tesis.alejofila.centrocomercial.db.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alejandro on 24/10/2015.
 */
public class CRUDManager {

    private static final String TAG = CRUDManager.class.getSimpleName();
    private SQLiteDatabase db;

    public CRUDManager(Context context) {
        MyDbHelper dbHelper = MyDbHelper.getInstance(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean productExits(Producto producto) {
        String table = TablesDB.Producto.TABLE_NAME;
        String[] columns = {"COUNT(*)"};
        String whereClause = TablesDB.Producto.CN_ID + " = ?";

        String whereArgs[] = {producto.getId() + ""};
        Cursor cursor = db.query(table, columns, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();
        int cantidad = cursor.getInt(0);
        cursor.close();

        return !(cantidad == 0);
    }

    public void insertProduct(Producto producto) {
        String table = TablesDB.Producto.TABLE_NAME;
        ContentValues values = new ContentValues();
        values.put(TablesDB.Producto.CN_ID, producto.getId());
        values.put(TablesDB.Producto.CN_NOMBRE, producto.getNombre());
        values.put(TablesDB.Producto.CN_PRECIO, Float.toString(producto.getPrecio()));
        values.put(TablesDB.Producto.CN_IMAGEN, producto.getRuta_imagen());
        db.insert(table, null, values);
    }

    public void insertOffer(Oferta oferta) {
        String table = TablesDB.Oferta.TABLE_NAME;
        ContentValues values = new ContentValues();
        values.put(TablesDB.Oferta.CN_FECHA_INICIO, oferta.getFechaInicio());
        values.put(TablesDB.Oferta.CN_DESCRIPCION, oferta.getDescripcion());
        values.put(TablesDB.Oferta.CN_FECHA_FINAL, oferta.getFechaFinal());
        values.put(TablesDB.Oferta.CN_TIENDA, oferta.getTienda());
        values.put(TablesDB.Oferta.CN_ID_PRODUCTO, oferta.getProducto().getId());
        db.insert(table, null, values);
    }

    public void updateOffer(Oferta oferta) {
        String table = TablesDB.Oferta.TABLE_NAME;
        String whereClause = TablesDB.Oferta.CN_ID_PRODUCTO + " = ?";
        String[] whereArgs = {oferta.getProducto().getId() + ""};
        ContentValues values = new ContentValues();
        values.put(TablesDB.Oferta.CN_DESCRIPCION, oferta.getDescripcion());
        values.put(TablesDB.Oferta.CN_FECHA_FINAL, oferta.getFechaFinal());
        db.update(table, values, whereClause, whereArgs);
    }

    public List<Oferta> listOffers(String currentDate) {
        Log.i(TAG,"current date: "+currentDate);
        String sqlString = "SELECT " + TablesDB.Producto.CN_NOMBRE + " , " + TablesDB.Producto.CN_PRECIO + " , " + TablesDB.Producto.CN_IMAGEN + " ,"
                + TablesDB.Oferta.CN_DESCRIPCION + " , " + TablesDB.Oferta.CN_FECHA_FINAL + " , " + TablesDB.Oferta.CN_TIENDA
                + " FROM " + TablesDB.Oferta.TABLE_NAME + " , " + TablesDB.Producto.TABLE_NAME
                + " WHERE " + TablesDB.Producto.TABLE_NAME + "." + TablesDB.Producto.CN_ID + " = " + TablesDB.Oferta.TABLE_NAME + "." + TablesDB.Oferta.CN_ID_PRODUCTO
                + " AND " + TablesDB.Oferta.CN_FECHA_FINAL + " >= Date('"+currentDate+"')"
        ;


        Log.d(TAG, "Sqlite to execute:  " + sqlString);

        Cursor cursorOfertas = db.rawQuery(sqlString, null);
        List<Oferta> ofertaList = new ArrayList<>();
        Log.i(TAG, "Cursor db size" + cursorOfertas.getCount());
        while (cursorOfertas.moveToNext()) {
            Oferta localOffer = new Oferta();
            Producto localProduct = new Producto();
            localProduct.setNombre(cursorOfertas.getString(0));
            localProduct.setPrecio(cursorOfertas.getFloat(1));
            localProduct.setRuta_imagen(cursorOfertas.getString(2));
            localOffer.setDescripcion(cursorOfertas.getString(3));
            localOffer.setFechaFinal(cursorOfertas.getString(4));
            localOffer.setTienda(cursorOfertas.getString(5));
            localOffer.setProducto(localProduct);
            ofertaList.add(localOffer);
        }
        cursorOfertas.close();

        return ofertaList;
    }
}
