package com.tesis.alejofila.centrocomercial;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.kogitune.activity_transition.ActivityTransition;
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
    private Toolbar toolbar;


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
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        viewPager = (ViewPager) this.findViewById(R.id.home_pager);
        TabLayout tabLayout = (TabLayout)this.findViewById(R.id.slidingTabs);
        ActivityTransition.with(getIntent()).to(viewPager).start(savedInstanceState);

        setSupportActionBar(toolbar);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }
}
