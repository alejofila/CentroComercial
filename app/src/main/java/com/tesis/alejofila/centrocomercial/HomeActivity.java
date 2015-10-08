package com.tesis.alejofila.centrocomercial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.tesis.alejofila.centrocomercial.helper.OfertaAdapter;
import com.tesis.alejofila.centrocomercial.http.Constants;
import com.tesis.alejofila.centrocomercial.model.Interes;
import com.tesis.alejofila.centrocomercial.model.InteresesFactory;
import com.tesis.alejofila.centrocomercial.model.Oferta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by alejofila on 4/09/15.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private ArrayList<Oferta> listOferta = new ArrayList<>();
    private ArrayList<ImageView> types = new ArrayList<>();
    private ArrayList<Interes> listaDeInteres = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle(getIntent().getStringExtra(Constants.EMAIL));
        loadIcons();
        cargaIntereses();
    }

    private void loadIcons(){
        //cargo los imageViews
        int [] icons = {R.id.type_1, R.id.type_2, R.id.type_3,
                R.id.type_4, R.id.type_5, R.id.type_6, R.id.type_7, R.id.type_8};
        for(int i = 0; i < 8; i++){
            types.add((ImageView)findViewById(icons[i]));
        }
        //Cargo los listeners
        Iterator <ImageView> imagenes = types.iterator();
        while (imagenes.hasNext()){
            imagenes.next().setOnClickListener(this);
        }
    }

    private void cargaIntereses() {
        String id = ParseInstallation.getCurrentInstallation().getObjectId();
        Iterator itChannels = null;
        try {
            List l = ParseInstallation.getCurrentInstallation().getList("channels");
            if (l != null)
                itChannels = l.iterator();
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }
        if (itChannels != null) {
            while (itChannels.hasNext()) {
                String channel = itChannels.next().toString();
                Log.i(TAG,"channel subscribed: "+ channel);
                Interes interes = InteresesFactory.makeInterest(channel);
                listaDeInteres.add(interes);
                verificaChannel(interes);
            }
        }
    }

    private void verificaChannel(Interes interes) {
        switch (interes.getChannel()) {
            case InteresesFactory.INTERES_VIDEO_GAMES:
                types.get(0).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            /*
            case InteresesFactory.INTERES_TECNOLOGIA:
                types.get(1).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_COMIDA_RAPIDA:
                types.get(2).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_JOYAS:
                types.get(3).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_ZAPATOS_MUJER:
                types.get(4).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_ROPA_MUJER:
                types.get(5).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_ZAPATOS_HOMBRE:
                types.get(6).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
                */
            case InteresesFactory.INTERES_ROPA_MASCULINA:
                types.get(7).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
        }
    }


    /*@Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String producto = intent.getStringExtra(Constants.PRODUCT_NAME);
        String precio = intent.getStringExtra(Constants.PRODUCT_PRICE);
        String url_imagen = intent.getStringExtra(Constants.PRODUCT_IMAGE);

        Oferta oferta = new Oferta(producto, precio, url_imagen);
        listOferta.add(0, oferta);
        adapter.notifyDataSetChanged();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            ParseUser.logOut();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.type_1:
                type_8Function(InteresesFactory.INTERES_VIDEO_GAMES, types.get(0));
                break;
            case R.id.type_2:
                type_8Function(InteresesFactory.INTERES_TECNOLOGIA, types.get(1));
                break;
            case R.id.type_3:
                type_8Function(InteresesFactory.INTERES_COMIDA_RAPIDA, types.get(2));
                break;
            case R.id.type_4:
                type_8Function(InteresesFactory.INTERES_JOYAS, types.get(3));
                break;
            case R.id.type_5:
                type_8Function(InteresesFactory.INTERES_ZAPATOS_MUJER, types.get(4));
                break;
            case R.id.type_6:
                type_8Function(InteresesFactory.INTERES_ROPA_MUJER, types.get(5));
                break;
            case R.id.type_7:
                type_8Function(InteresesFactory.INTERES_ZAPATOS_HOMBRE, types.get(6));
                break;
            case R.id.type_8:
                type_8Function(InteresesFactory.INTERES_ROPA_MASCULINA, types.get(7));
                break;
        }

    }

    private void type_8Function(String interes, ImageView icon) {
        Boolean finded = false;
        Interes auxInteres = null;
        for (Interes interesl : listaDeInteres) {
            if (interesl.getChannel().toString() == interes) {
                icon.setImageResource(interesl.getIcono1());
                unsubscribeTo(interesl.getChannel().toString());
                auxInteres = interesl;
                Log.i(TAG, "Desuscribiendo" + interes);
                finded = true;
            }
        }
        listaDeInteres.remove(auxInteres);
        if (finded != true) {
            Interes nuevo = InteresesFactory.makeInterest(interes);
            icon.setImageResource(nuevo.getIcono2());
            subscribeTo(nuevo.getChannel().toString());
            listaDeInteres.add(nuevo);
            Log.i(TAG, "Suscribiendo" + interes);
        }
    }


    private void unsubscribeTo(String channel) {
        ParsePush.unsubscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    Toast.makeText(HomeActivity.this, "Hubo un error al actualizar tus interes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Tus intereses han sido actualizados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void subscribeTo(String channel) {
        ParsePush.subscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    Toast.makeText(HomeActivity.this, "Hubo un error al actualizar tus interes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(HomeActivity.this, "Tus intereses han sido actualizados", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
