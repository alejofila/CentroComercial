package com.tesis.alejofila.centrocomercial;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.tesis.alejofila.centrocomercial.adapter.MyFragmentPagerAdapter;
import com.tesis.alejofila.centrocomercial.http.Constants;
import com.tesis.alejofila.centrocomercial.model.Interes;

import java.util.ArrayList;

/**
 * Created by Alejandro on 18/10/2015.
 */
public class HomeActivity2  extends AppCompatActivity{

    /**
     * NON UI VARIABLES
     */
    private static final String TAG = HomeActivity2.class.getSimpleName();


    /**
     * UI_VARIABLES
     */
    private ViewPager viewPager;


    /**
     * Activity_lifecycle_methods
     */
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getIntent().getStringExtra(Constants.EMAIL));
        setContentView(R.layout.activity_home_2);
        viewPager = (ViewPager) this.findViewById(R.id.home_pager);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = (TabLayout)this.findViewById(R.id.slidingTabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
