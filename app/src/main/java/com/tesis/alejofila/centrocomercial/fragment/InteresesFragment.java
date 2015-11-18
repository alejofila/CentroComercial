package com.tesis.alejofila.centrocomercial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.tesis.alejofila.centrocomercial.R;
import com.tesis.alejofila.centrocomercial.model.Interes;
import com.tesis.alejofila.centrocomercial.model.InteresesFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alejandro on 18/10/2015.
 */
public class InteresesFragment extends Fragment implements View.OnClickListener {


    public final static String TAG = InteresesFragment.class.getSimpleName();
    /**
     * UI VARIABLES
     */
    private ArrayList<ImageView> types = new ArrayList<>();
    private View rootView;

    /**
     * NON UI VARIABLES
     */
    private ArrayList<Interes> listaDeInteres = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_intereses, container, false);
        loadIcons();
        cargaIntereses();
        return rootView;
    }


    private void loadIcons() {
        //cargo los imageViews
        final int[] icons = {R.id.type_1, R.id.type_2, R.id.type_3,
                R.id.type_4, R.id.type_5, R.id.type_6, R.id.type_7, R.id.type_8};
        for (int i = 0; i < 8; i++)
            types.add((ImageView) rootView.findViewById(icons[i]));

        //Cargo los listeners
        Iterator<ImageView> imagenes = types.iterator();
        while (imagenes.hasNext()) {
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
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (itChannels != null) {
            while (itChannels.hasNext()) {
                String channel = itChannels.next().toString();
                Log.i(TAG, "channel subscribed: " + channel);
                Interes interes = InteresesFactory.makeInterest(channel);

                if (interes != null) {
                    listaDeInteres.add(interes);
                    verificaChannel(interes);
                }
            }
        }
    }

    private void verificaChannel(Interes interes) {
        switch (interes.getChannel()) {
            case InteresesFactory.INTERES_VIDEO_GAMES:
                types.get(0).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;

            case InteresesFactory.INTERES_TECNOLOGIA:
                types.get(1).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_COMIDA_RAPIDA:
                types.get(2).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            case InteresesFactory.INTERES_ACCESORIOS:
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

            case InteresesFactory.INTERES_ROPA_MASCULINA:
                types.get(7).setImageResource(interes.getIcono2());
                interes.setActivado(true);
                break;
            default:
                break;
        }
    }





    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.type_1:
                activaDesactivaInteres(InteresesFactory.INTERES_VIDEO_GAMES, types.get(0));
                break;
            case R.id.type_2:
                activaDesactivaInteres(InteresesFactory.INTERES_TECNOLOGIA, types.get(1));
                break;
            case R.id.type_3:
                activaDesactivaInteres(InteresesFactory.INTERES_COMIDA_RAPIDA, types.get(2));
                break;
            case R.id.type_4:
                activaDesactivaInteres(InteresesFactory.INTERES_ACCESORIOS, types.get(3));
                break;
            case R.id.type_5:
                activaDesactivaInteres(InteresesFactory.INTERES_ZAPATOS_MUJER, types.get(4));
                break;
            case R.id.type_6:
                activaDesactivaInteres(InteresesFactory.INTERES_ROPA_MUJER, types.get(5));
                break;
            case R.id.type_7:
                activaDesactivaInteres(InteresesFactory.INTERES_ZAPATOS_HOMBRE, types.get(6));
                break;
            case R.id.type_8:
                activaDesactivaInteres(InteresesFactory.INTERES_ROPA_MASCULINA, types.get(7));
                break;
        }

    }

    private void activaDesactivaInteres(String interes, ImageView icon) {
        Boolean finded = false;
        Interes auxInteres = null;
        for (Interes interesl : listaDeInteres) {
            if (interesl.getChannel().equals(interes)) {
                icon.setImageResource(interesl.getIcono1());
                unsubscribeTo(interesl.getChannel());
                auxInteres = interesl;
                Log.i(TAG, "Desuscribiendo" + interes);
                finded = true;
            }
        }
        listaDeInteres.remove(auxInteres);
        if (!finded) {
            Interes nuevo = InteresesFactory.makeInterest(interes);
            if(nuevo != null) {
                icon.setImageResource(nuevo.getIcono2());
                subscribeTo(nuevo.getChannel().toString());
                listaDeInteres.add(nuevo);
                Log.i(TAG, "Suscribiendo" + interes);
            }
        }
    }


    private void unsubscribeTo(String channel) {
        ParsePush.unsubscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    Toast.makeText(getActivity(), "Hubo un error al actualizar tus interes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Tus intereses han sido actualizados", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void subscribeTo(String channel) {
        ParsePush.subscribeInBackground(channel, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    Toast.makeText(getActivity(), "Hubo un error al actualizar tus interes", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Tus intereses han sido actualizados", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
