package com.tesis.alejofila.centrocomercial.model;

import com.tesis.alejofila.centrocomercial.R;

/**
 * Created by MARIO RANGEL on 09/09/2015.
 */
public class InteresesFactory {

    public static final String INTERES_VIDEO_GAMES = "video_games";
    public static final String INTERES_ROPA_MASCULINA = "ropa_masculina";
    public static final String INTERES_COMIDA_RAPIDA = "comida";
    public static final String INTERES_ACCESORIOS = "accesorios";
    public static final String INTERES_ZAPATOS_HOMBRE = "zapatos_hombre";
    public static final String INTERES_ZAPATOS_MUJER = "zapatos_mujer";
    public static final String INTERES_TECNOLOGIA = "tecnologia";
    public static final String INTERES_ROPA_MUJER = "ropa_mujer";



    public static Interes makeInterest (final String channel){
        switch(channel){
            case INTERES_VIDEO_GAMES:
                return new Interes(INTERES_VIDEO_GAMES, R.drawable.ic_type_video_game_2, R.drawable.ic_type_video_games);
            case INTERES_ROPA_MASCULINA:
                return new Interes(INTERES_ROPA_MASCULINA, R.drawable.ic_type_clothes_2, R.drawable.ic_type_clothes);
            case INTERES_COMIDA_RAPIDA:
                return new Interes(INTERES_COMIDA_RAPIDA, R.drawable.ic_type_fast_food_2, R.drawable.ic_type_fast_food);
            case INTERES_ACCESORIOS:
                return new Interes(INTERES_ACCESORIOS, R.drawable.ic_type_jewel_2, R.drawable.ic_type_jewel);
            case INTERES_ZAPATOS_HOMBRE:
                return new Interes(INTERES_ZAPATOS_HOMBRE, R.drawable.ic_type_men_shoe_2, R.drawable.ic_type_men_shoe);
            case INTERES_TECNOLOGIA:
                return new Interes(INTERES_TECNOLOGIA, R.drawable.ic_type_techno_2, R.drawable.ic_type_techno);
            case INTERES_ZAPATOS_MUJER:
                return new Interes(INTERES_ZAPATOS_MUJER, R.drawable.ic_type_women_shoe_2, R.drawable.ic_type_women_shoe);
            case INTERES_ROPA_MUJER:
                return new Interes(INTERES_ROPA_MUJER, R.drawable.ic_type_women_dress_2, R.drawable.ic_type_women_dress);
            default:
                return null;
        }
    }
}
