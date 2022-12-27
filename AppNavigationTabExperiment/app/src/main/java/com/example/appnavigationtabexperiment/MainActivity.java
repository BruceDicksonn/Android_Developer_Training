package com.example.appnavigationtabexperiment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("Top Stories"));
        tabLayout.addTab(tabLayout.newTab().setText("Tech news"));
        tabLayout.addTab(tabLayout.newTab().setText("Cooking"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // configura/ registra um listener para clicks no tabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}