package com.example.appslidingtabs;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.appslidingtabs.Fragment.EmAltaFragment;
import com.example.appslidingtabs.Fragment.HomeFragment;
import com.example.appslidingtabs.Fragment.InscricoesFragment;
import com.example.appslidingtabs.databinding.ActivityMainBinding;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setElevation(0);

        // Ao invés de criarmos uma classe que extende o FragmentPageAdapter, a lib já disponibiliza uma classe montada pra isso
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Home", HomeFragment.class)
                .add("Inscrições", InscricoesFragment.class)
                .add("Em alta", EmAltaFragment.class)
                .create());


        binding.viewpager.setAdapter(adapter);
        binding.viewPagerTab.setViewPager(binding.viewpager);

    }
}