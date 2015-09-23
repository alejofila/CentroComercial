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
import java.util.Iterator;
import java.util.List;

/**
 * Created by tales on 4/09/15.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ListView listView;
    private ArrayList<Oferta> listOferta = new ArrayList<>();
    private OfertaAdapter adapter;
    private ImageView type1, type8;
    private boolean type1_activated;
    private boolean type2_activated;
    private ArrayList<Interes> listaDeInteres = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        type1_activated = false;
        type2_activated = false;
        setTitle(getIntent().getStringExtra(Constants.EMAIL));
        type1 = (ImageView) findViewById(R.id.type_1);
        type1.setOnClickListener(this);
        type8 = (ImageView) findViewById(R.id.type_8);
        type8.setOnClickListener(this);
        cargaIntereses();

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
                type1.setImageResource(interes.getIcono1());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_TECNOLOGIA:
                type2.setImageResource(interes.getIcono1());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_ROPA_MASCULINA:
                type8.setImageResource(interes.getIcono1());
                interes.setActivado(true);
                break;
        }
    }

    private boolean userHasNoSubscriptions() {
        return
                false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String producto = intent.getStringExtra(Constants.PRODUCT_NAME);
        String precio = intent.getStringExtra(Constants.PRODUCT_PRICE);
        String url_imagen = intent.getStringExtra(Constants.PRODUCT_IMAGE);

        Oferta oferta = new Oferta(producto, precio, url_imagen);
        listOferta.add(0, oferta);
        adapter.notifyDataSetChanged();
    }


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
                type_1Function();
                //InteresRopaFemenina ropaFemenina = new InteresRopaFemenina();
                break;
            case R.id.type_8:
                type_8Function();
                break;

        }

    }

    private void type_8Function() {
        if (!type2_activated) {
            type8.setImageResource(R.drawable.ic_type_clothes);
            Log.i(TAG, "Subscribiendo");
            subscribeTo("camisetas");
        } else {
            type8.setImageResource(R.drawable.ic_type_clothes_2);
            Log.i(TAG, "Desubscribiendo");

            unsubscribeTo("camisetas");


        }
        type2_activated = !type2_activated;

    }


    private void type_1Function() {
        if (!type1_activated) {
            type1.setImageResource(R.drawable.ic_type_video_games);
            Log.i(TAG, "Subscribiendo");
            subscribeTo("video_games");
        } else {
            type1.setImageResource(R.drawable.ic_type_video_game_2);
            Log.i(TAG, "Desubscribiendo");

            unsubscribeTo("video_games");


        }
        type1_activated = !type1_activated;

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
