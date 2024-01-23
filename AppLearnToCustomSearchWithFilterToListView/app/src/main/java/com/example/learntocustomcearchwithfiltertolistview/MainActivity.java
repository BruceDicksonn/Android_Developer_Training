package com.example.learntocustomcearchwithfiltertolistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.learntocustomcearchwithfiltertolistview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<Country> countryList = new ArrayList<>();
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fillList();

        // create and set a custom adapter to listView
        adapter = new CustomAdapter(this, R.layout.custom_adapter_item_layout, countryList);
        binding.listCountry.setAdapter( adapter );
        binding.editSearch.addTextChangedListener(watcher);

    }

    void fillList(){

        countryList.add(new Country(1, R.drawable.brazil, "Brazil"));
        countryList.add(new Country(2, R.drawable.canada, "Canada"));
        countryList.add(new Country(3, R.drawable.china, "China"));
        countryList.add(new Country(4, R.drawable.france, "France"));
        countryList.add(new Country(5, R.drawable.germany, "Germany"));
        countryList.add(new Country(6, R.drawable.united_kingdom, "United Kingdom"));
        countryList.add(new Country(7, R.drawable.united_states, "USA"));

    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String q = charSequence.toString().toLowerCase();
            adapter.getFilter().filter(q);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}