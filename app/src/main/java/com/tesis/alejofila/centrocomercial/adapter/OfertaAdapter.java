package com.tesis.alejofila.centrocomercial.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tesis.alejofila.centrocomercial.R;
import com.tesis.alejofila.centrocomercial.model.Oferta;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alejandro on 19/10/2015.
 */
public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.OfertaViewHolder> {

    List<Oferta> ofertas ;
    Context context;

    public OfertaAdapter(List<Oferta> ofertas, Context context, OnOfertaListaClicked mOnOfertaListaClicked){
        this.ofertas= ofertas;
        this.context = context;
        this.mOnOfertaListaClicked = mOnOfertaListaClicked;


    }
    @Override
    public OfertaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.oferta_item, parent, false);

        return new OfertaViewHolder(itemView,mOnOfertaListaClicked);
    }

    @Override
    public void onBindViewHolder(OfertaViewHolder holder, int position) {
        final Oferta oferta= ofertas.get(position);
        holder.txtNombre.setText(oferta.getProducto().getNombre());
        Picasso.with(context)
                .load(oferta.getProducto().getRuta_imagen())
                .into(holder.imgProducto);

        holder.cardContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnOfertaListaClicked.onOfertaClicked(oferta);
            }
        });



    }


    @Override
    public int getItemCount() {
        return ofertas.size();
    }

    public static class OfertaViewHolder extends RecyclerView.ViewHolder{
        protected TextView txtNombre;
        protected ImageView imgProducto;
        protected CardView cardContainer;
        public OfertaViewHolder(View itemView, final OnOfertaListaClicked mOnOfertaListaClicked) {
            super(itemView);
            cardContainer = (CardView) itemView.findViewById(R.id.oferta_card_container);
            txtNombre = (TextView) itemView.findViewById(R.id.nombre_pruducto_oferta);
            imgProducto =(ImageView) itemView.findViewById(R.id.imagen_oferta);
        }
    }

    public interface OnOfertaListaClicked{
        void onOfertaClicked(Oferta oferta);
    }
    private OnOfertaListaClicked mOnOfertaListaClicked;

}
