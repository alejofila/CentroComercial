package com.tesis.alejofila.centrocomercial.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kogitune.activity_transition.ActivityTransitionLauncher;
import com.tesis.alejofila.centrocomercial.DetalleActivity;
import com.tesis.alejofila.centrocomercial.R;
import com.tesis.alejofila.centrocomercial.adapter.OfertaAdapter;
import com.tesis.alejofila.centrocomercial.db.CRUDManager;
import com.tesis.alejofila.centrocomercial.model.Oferta;
import com.tesis.alejofila.centrocomercial.utils.DateUtils;
import com.tesis.alejofila.centrocomercial.utils.ParsingUtils;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Alejandro on 18/10/2015.
 */
public class UltimasOfertasFragment extends Fragment {

    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ultimas_ofertas, container, false);
        recyclerView  = (RecyclerView) rootView.findViewById(R.id.lista_ofertas);
        recyclerView.setHasFixedSize(true);
        CRUDManager crudManager = new CRUDManager(getActivity());
        GregorianCalendar currentDate = new GregorianCalendar();
        String dateString = DateUtils.parseCalendar(currentDate);
        List<Oferta> ofertasList = crudManager.listOffers(dateString);
        OfertaAdapter adapter = new OfertaAdapter(ofertasList, getActivity(),mOnOfertaListaClicked );
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    OfertaAdapter.OnOfertaListaClicked mOnOfertaListaClicked = new OfertaAdapter.OnOfertaListaClicked() {
        @Override
        public void onOfertaClicked(Oferta oferta,View view) {
            Bundle args = ParsingUtils.ofertaToBundle(oferta);
            Intent intent = new Intent(getActivity(), DetalleActivity.class);
            intent.putExtras(args);
            ActivityTransitionLauncher.with(getActivity()).from(view).launch(intent);

        }
    };
}
