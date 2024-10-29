package com.example.apptrainingstepper;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptrainingstepper.adapters.ViewPagerAdapter;
import com.example.apptrainingstepper.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ViewPagerAdapter.enumAction action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int orientation = getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            fillTabLayoutPortrait();
        } else {
            fillTabLayoutLandscape();
            fillEvents();
        }

    }

    private void fillEvents() {

        binding.btnNext.setOnClickListener((view) -> {
            action = ViewPagerAdapter.enumAction.NEXT;
            int pos = ((ViewPagerAdapter) Objects.requireNonNull(binding.viewPager.getAdapter())).next();
            binding.tabLayout.selectTab( binding.tabLayout.getTabAt(pos) );
        });

        binding.btnPrev.setOnClickListener((view) -> {
            action = ViewPagerAdapter.enumAction.PREVIOUS;
            int pos = ((ViewPagerAdapter) Objects.requireNonNull(binding.viewPager.getAdapter())).back();
            binding.tabLayout.selectTab( binding.tabLayout.getTabAt(pos) );
        });

    }

    private void fillComponentsPortrait(){

        int progress    = ((ViewPagerAdapter)binding.viewPager.getAdapter()).getCurrentPageIndex() + 1;
        int position    = ((ViewPagerAdapter)binding.viewPager.getAdapter()).getCurrentPageIndex();
        int maxProgress = binding.viewPager.getAdapter().getItemCount();

        binding.progress.setProgress( progress );
        binding.progress.setMax(maxProgress);

        binding.labelProgress.setText(String.format("%d of %d", (position + 1), binding.viewPager.getAdapter().getItemCount()));
        binding.labelPage.setText( ((ViewPagerAdapter)binding.viewPager.getAdapter()).getPageName(position) );

        binding.labelNextPage.setText(String.format("Next page: %s", ((ViewPagerAdapter) binding.viewPager.getAdapter()).getPageName(position + 1)));

        if((position+1) == binding.viewPager.getAdapter().getItemCount()) {
            binding.labelNextPage.setText(String.format("Back page: %s", ((ViewPagerAdapter) binding.viewPager.getAdapter()).getPageName(position-1)));
        }

    }

    private void fillTabLayoutPortrait() {
        binding.viewPager.setAdapter( new ViewPagerAdapter(this));

        fillComponentsPortrait();

        binding.viewPager.setUserInputEnabled(false); // desativa o swipe

        binding.btnNext.setOnClickListener((view) -> {

            action = ViewPagerAdapter.enumAction.NEXT;
            int pos = ((ViewPagerAdapter) Objects.requireNonNull(binding.viewPager.getAdapter())).next();
            binding.viewPager.setCurrentItem(pos);

            if(pos > 0) binding.btnPrev.setVisibility(View.VISIBLE);

            if((pos+1) == binding.viewPager.getAdapter().getItemCount()) binding.btnNext.setVisibility(View.GONE);

            fillComponentsPortrait();

        });

        binding.btnPrev.setOnClickListener((view) -> {

            action = ViewPagerAdapter.enumAction.PREVIOUS;
            int pos = ((ViewPagerAdapter) Objects.requireNonNull(binding.viewPager.getAdapter())).back();
            binding.viewPager.setCurrentItem(pos);

            if(pos == 0) {
                binding.btnPrev.setVisibility(View.GONE);
                binding.btnNext.setVisibility(View.VISIBLE);
            }

            fillComponentsPortrait();

        });

    }

    private void fillTabLayoutLandscape() {
        binding.viewPager.setAdapter( new ViewPagerAdapter(this));

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {

            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.custom_tab_item, null);

            View separator = view.findViewById(R.id.tabItemSeparator);
            TextView txtView = view.findViewById(R.id.labelTabPage);
            TextView txtViewStep = view.findViewById(R.id.labelStep);

            txtView.setText( ((ViewPagerAdapter)binding.viewPager.getAdapter()).getPageName(position) );
            txtViewStep.setText(String.valueOf(position+1));

            if( binding.viewPager.getAdapter().getItemCount() == (position+1) ) separator.setVisibility(View.GONE);

            tab.setCustomView(view);

        }).attach();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = ((ViewPagerAdapter) binding.viewPager.getAdapter()).getCurrentPageIndex();
                boolean isChecked;

                View view = tab.getCustomView();

                if( action == ViewPagerAdapter.enumAction.NEXT ) {
                    isChecked = ((ViewPagerAdapter) binding.viewPager.getAdapter()).isChecked(position-1);
                } else {
                    isChecked = ((ViewPagerAdapter) binding.viewPager.getAdapter()).isChecked(position+1);
                    if (isChecked) {
                        view.findViewById(R.id.linearTabIcon).setBackground(getDrawable(R.drawable.circle_shape));
                        view.findViewById(R.id.labelStep).setVisibility(View.VISIBLE);
                        view.findViewById(R.id.stepIcon).setVisibility(View.GONE);
                    }
                }

                ((TextView) view.findViewById(R.id.labelTabPage) ).setTypeface( Typeface.DEFAULT_BOLD );

                binding.linearButtons.findViewById(R.id.btnPrev).setVisibility(View.VISIBLE);

                if( position == 0 ) {
                    binding.linearButtons.findViewById(R.id.btnPrev).setVisibility(View.GONE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                int position = ((ViewPagerAdapter) binding.viewPager.getAdapter()).getCurrentPageIndex();
                boolean isChecked;

                if( action == ViewPagerAdapter.enumAction.NEXT ) {
                    isChecked = ((ViewPagerAdapter) binding.viewPager.getAdapter()).isChecked(position-1);
                } else {
                    isChecked = ((ViewPagerAdapter) binding.viewPager.getAdapter()).isChecked(position+1);
                }

                View view = tab.getCustomView();

                if( isChecked ) {
                    view.findViewById(R.id.linearTabIcon).setBackground(getDrawable(R.drawable.circle_shape_active));
                    view.findViewById(R.id.labelStep).setVisibility(View.GONE);
                    view.findViewById(R.id.stepIcon).setVisibility(View.VISIBLE);
                    return;
                }

                view.findViewById(R.id.linearTabIcon).setBackground(getDrawable(R.drawable.circle_shape));
                view.findViewById(R.id.labelStep).setVisibility(View.VISIBLE);
                view.findViewById(R.id.stepIcon).setVisibility(View.GONE);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = ((ViewPagerAdapter) binding.viewPager.getAdapter()).getCurrentPageIndex();

                View view = tab.getCustomView();

                ((TextView) view.findViewById(R.id.labelTabPage) ).setTypeface( Typeface.DEFAULT_BOLD );

                if( position == 0 ) {
                    binding.linearButtons.findViewById(R.id.btnPrev).setVisibility(View.GONE);
                }

            }
        });
        binding.viewPager.setUserInputEnabled(false); // desativa o swipe
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
    }
}