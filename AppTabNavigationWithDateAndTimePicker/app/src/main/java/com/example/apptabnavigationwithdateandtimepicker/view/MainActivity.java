package com.example.apptabnavigationwithdateandtimepicker.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.apptabnavigationwithdateandtimepicker.R;
import com.example.apptabnavigationwithdateandtimepicker.adapters.PagerAdapter;
import com.example.apptabnavigationwithdateandtimepicker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle("Navigation");
        setSupportActionBar(binding.toolbar);

        initComponents();

    }

    private void initComponents() {

        TabLayout tabLayout = binding.tabLayout;

        tabLayout.addTab(tabLayout.newTab().setText("Alert"));
        tabLayout.addTab(tabLayout.newTab().setText("Date"));
        tabLayout.addTab(tabLayout.newTab().setText("Time"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        ViewPager viewPager = binding.pager;
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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