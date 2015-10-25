package com.tesis.alejofila.centrocomercial.utils;

import android.os.Bundle;
import android.util.Log;

import com.tesis.alejofila.centrocomercial.http.Constants;
import com.tesis.alejofila.centrocomercial.model.Oferta;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by alejofila on 6/09/15.
 */
public class ParsingUtils {
    private static final String TAG = ParsingUtils.class.getSimpleName();

    public static Bundle detailedJsonToBundle(JSONObject jObj) {
        Bundle b = new Bundle();

        try {
            b.putString(Constants.PRODUCT_IMAGE, jObj.getString(Constants.PRODUCT_IMAGE));
            b.putInt(Constants.PRODUCT_ID, jObj.getInt(Constants.PRODUCT_ID));
            b.putString(Constants.PRODUCT_NAME, jObj.getString(Constants.PRODUCT_NAME));
            b.putFloat(Constants.PRODUCT_PRICE, (float) jObj.getDouble(Constants.PRODUCT_PRICE));
            b.putString(Constants.OFFER_DESCRITION, jObj.getString(Constants.OFFER_DESCRITION));
            b.putString(Constants.OFFER_STORE, jObj.getString(Constants.OFFER_STORE));
            b.putString(Constants.OFFER_TO_DATE, jObj.getString(Constants.OFFER_TO_DATE));
            b.putString(Constants.OFFER_SINCE_DATE, jObj.getString(Constants.OFFER_SINCE_DATE));
        } catch (JSONException e) {
            Log.e(TAG, "ERROR PARSEANDO JSON");
            e.printStackTrace();
        }
        return b;
    }

    public static Bundle ofertaToBundle(Oferta oferta) {
        Bundle b = new Bundle();
        b.putString(Constants.PRODUCT_IMAGE, oferta.getProducto().getRuta_imagen());
        b.putInt(Constants.PRODUCT_ID, oferta.getProducto().getId());
        b.putString(Constants.PRODUCT_NAME, oferta.getProducto().getNombre());
        b.putFloat(Constants.PRODUCT_PRICE, oferta.getProducto().getPrecio());
        b.putString(Constants.OFFER_DESCRITION, oferta.getDescripcion());
        b.putString(Constants.OFFER_STORE, oferta.getTienda());
        b.putString(Constants.OFFER_TO_DATE, oferta.getFechaFinal());
        b.putString(Constants.OFFER_SINCE_DATE, oferta.getFechaInicio());
        return b;
    }


}