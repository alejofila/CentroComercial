package com.tesis.alejofila.centrocomercial.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tesis.alejofila.centrocomercial.fragment.InteresesFragment;
import com.tesis.alejofila.centrocomercial.fragment.UltimasOfertasFragment;

/**
 * Created by Alejandro on 18/10/2015.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {


    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch (position) {
            case 0:
                f = new InteresesFragment();
                break;
            case 1:
                f = new UltimasOfertasFragment();
                break;
        }
        return f;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = "";
        switch (position) {
            case 0:
                pageTitle = "Intereses";
                break;
            case 1:
                pageTitle = "Ofertas";
                break;

        }
        return pageTitle;
    }
}
