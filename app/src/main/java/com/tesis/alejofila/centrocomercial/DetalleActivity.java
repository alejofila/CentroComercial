package com.tesis.alejofila.centrocomercial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.alejofila.centrocomercial.helper.CircleTransform;
import com.tesis.alejofila.centrocomercial.http.Constants;

/**
 * Created by alejofila on 6/09/15.
 */
public class DetalleActivity extends AppCompatActivity {

    private static final String TAG = DetalleActivity.class.getSimpleName();
    /**
     * UI VARIABLES
     */
    private ImageView imagen;
    private TextView txtProductName;
    private TextView txtProductPrice;
    private TextView txtOfferStore;
    private TextView txtOfferDescription;

    private TextView txtProductFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Calling onCreate in DetallecActivity");
        setContentView(R.layout.activity_product_detail);
        imagen = (ImageView) findViewById(R.id.imagen_product_detail);
        txtProductName = (TextView) findViewById(R.id.txt_product_name);
        txtProductPrice = (TextView) findViewById(R.id.precio_product_detail);
        txtOfferStore = (TextView) findViewById(R.id.store_product);
        txtOfferDescription = (TextView) findViewById(R.id.description_product_detail);
        txtProductFecha        = (TextView) findViewById(R.id.txt_fecha_valida);
        //getSupportActionBar().setHomeButtonEnabled(true);


        if (getIntent() != null) {
            Bundle b = getIntent().getExtras();

            String productName = b.getString(Constants.PRODUCT_NAME);
            String productImage = b.getString(Constants.PRODUCT_IMAGE);
            float productPrice = b.getFloat(Constants.PRODUCT_PRICE);
            String productStore = b.getString(Constants.OFFER_STORE);
            String offerDescription = b.getString(Constants.OFFER_DESCRITION);
            String fecha = "Valido hasta "+b.getString(Constants.OFFER_TO_DATE);
            setProductData(productName, productPrice, productImage, productStore,fecha, offerDescription);
        }

    }

    private void setProductData(String productName, float productPrice, String productImage, String productStore, String date, String description) {
        txtOfferDescription.setText(description);
        txtProductName.setText(productName);
        txtOfferStore.setText("Encuentralo en: " + productStore);
        txtProductPrice.setText("$" + productPrice);
        txtProductFecha.setText(date);
        Picasso.with(this).load(productImage)
                .placeholder(R.mipmap.my_ic_launcher)
                .transform(new CircleTransform())
                .into(imagen);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "Calling onNew intent in DetalleActivity");
    }
}
