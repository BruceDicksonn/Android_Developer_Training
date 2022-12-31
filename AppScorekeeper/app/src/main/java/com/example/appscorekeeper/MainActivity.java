package com.example.appscorekeeper;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.appscorekeeper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    private ActivityMainBinding binding;
    private int mScore1 = 0;
    private int mScore2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState != null){

            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            binding.score1.setText(String.valueOf(mScore1));
            binding.score2.setText(String.valueOf(mScore2));

        }

    }

    public void increaseScore(View view) {

        int viewId = view.getId();

        switch (viewId) {

            case R.id.increaseTeam1:

                mScore1++;
                binding.score1.setText(String.valueOf(mScore1));

            break;
            case R.id.increaseTeam2:

                mScore2++;
                binding.score2.setText(String.valueOf(mScore2));

            break;

        }

    }

    public void decreaseScore(View view) {
        int viewId = view.getId();

        switch (viewId) {

            case R.id.decreaseTeam1:

                mScore1--;
                binding.score1.setText(String.valueOf(mScore1));

                break;
            case R.id.decreaseTeam2:

                mScore2--;
                binding.score2.setText(String.valueOf(mScore2));

                break;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.night_mode:

                int nightMode = AppCompatDelegate.getDefaultNightMode();

                if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                } else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                }

                recreate();

            break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);

        super.onSaveInstanceState(outState);
    }
}