package com.tesis.alejofila.centrocomercial.receiver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;
import com.parse.ParseUser;
import com.tesis.alejofila.centrocomercial.DetalleActivity;
import com.tesis.alejofila.centrocomercial.HomeActivity;
import com.tesis.alejofila.centrocomercial.db.CRUDManager;
import com.tesis.alejofila.centrocomercial.helper.NotificationUtils;
import com.tesis.alejofila.centrocomercial.http.Constants;
import com.tesis.alejofila.centrocomercial.model.Oferta;
import com.tesis.alejofila.centrocomercial.model.Producto;
import com.tesis.alejofila.centrocomercial.utils.ParsingUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by alejofila on 5/09/15.
 */
public class CustomPushReceiver extends ParsePushBroadcastReceiver {
    private final String TAG = CustomPushReceiver.class.getSimpleName();
    private Context mContext;

    private NotificationUtils notificationUtils;

    private Intent parseIntent;

    public CustomPushReceiver() {
        super();
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);
        mContext = context;
        if (!isParseUserLogin())
            return;
        if (intent == null)
            return;
        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

            Log.e(TAG, "Push received: " + json);

            parseIntent = intent;
            int notificationType = determineNotificationType(json);


            switch (notificationType) {
                case Constants.NOTIFICATION_TYPE_SINGLE:
                    parseSingleJSON(context, json);
                    break;
                case Constants.NOTIFICATION_TYPE_MULTIPLE:
                    parseMultipleJSON(context, json);
                    break;
                default:
                    break;
            }

        } catch (JSONException e) {
            Log.e(TAG, "Push message json exception: 1 " + e.getMessage());
        }
    }

    private boolean isParseUserLogin() {
        ParseUser user = ParseUser.getCurrentUser();
        return user != null;

    }

    private void parseMultipleJSON(Context context, JSONObject json) {
    }

    private void parseSingleJSON(Context context, JSONObject json) {
        Log.i(TAG, "ENTERING in parseSingleJSON");
        try {
            boolean isBackground = json.getBoolean("is_background");
            JSONObject data = json.getJSONObject("data");

            Bundle extractedParams = ParsingUtils.detailedJsonToBundle(data);

            saveOfferLocally(extractedParams);

            String title = json.getString("titulo");
            String message = json.getString("message");


            if (!isBackground) {
                Intent resultIntent = new Intent(context, DetalleActivity.class);
                resultIntent.putExtras(extractedParams);
                showNotificationMessage(context, title, message, resultIntent);

            }


        } catch (JSONException e) {
            Log.e(TAG, "Push message json exception: 2" + e.getMessage());
        }
    }

    /**
     *
     * @param info the incoming offer info
     */
    private void saveOfferLocally(Bundle info) {
        //Filling product information
        Producto producto = new Producto();
        producto.setId(info.getInt(Constants.PRODUCT_ID));
        producto.setNombre(info.getString(Constants.PRODUCT_NAME));
        producto.setPrecio(info.getFloat(Constants.PRODUCT_PRICE));
        producto.setRuta_imagen(info.getString(Constants.PRODUCT_IMAGE));
        //Filling offer information
        Oferta oferta =new Oferta();
        oferta.setDescripcion(info.getString(Constants.OFFER_DESCRITION));
        oferta.setFechaInicio(info.getString(Constants.OFFER_SINCE_DATE));
        oferta.setFechaFinal(info.getString(Constants.OFFER_TO_DATE));
        oferta.setTienda(info.getString(Constants.OFFER_STORE));
        oferta.setProducto(producto);

        CRUDManager crudManager = new CRUDManager(mContext);
        if(!crudManager.productExits(producto)) {
            Log.i(TAG,"El producto no existe sera agregado");
            crudManager.insertProduct(producto);
            crudManager.insertOffer(oferta);
        }
        else{
            Log.i(TAG,"El producto si existe solo sera actualizada la oferta");
            crudManager.updateOffer(oferta);
        }

    }


    private int determineNotificationType(JSONObject json) {
        try {
            int type = json.getInt(Constants.NOTIFICATION_TYPE);
            return type;
        } catch (JSONException e) {
            Log.e(TAG, "There is no such field 'type' in incoming JSON");
            e.printStackTrace();
            return Constants.NO_NOTIFICATION_TYPE;

        }
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    /**
     * Parses the push notification json
     *
     * @param context
     * @param json
     */
    private void parsePushJson(Context context, JSONObject json) {
        try {
            boolean isBackground = json.getBoolean("is_background");
            JSONObject data = json.getJSONObject("data");
            String title = json.getString("title");
            Log.i(TAG, "this should be the title " + title);

            String message = json.getString("message");
            Log.i(TAG, "this should be the message " + message);


            if (!isBackground) {
                Intent resultIntent = new Intent(context, HomeActivity.class);
                showNotificationMessage(context, title, message, resultIntent);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Push message json exception: 2" + e.getMessage());
        }
    }


    /**
     * Shows the notification message in the notification bar
     * If the app is in background, launches the app
     *
     * @param context
     * @param title
     * @param message
     * @param intent
     */
    private void showNotificationMessage(Context context, String title, String message, Intent intent) {

        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, intent);
    }
}