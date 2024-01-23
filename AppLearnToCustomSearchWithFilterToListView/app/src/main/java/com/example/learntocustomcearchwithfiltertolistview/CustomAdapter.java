package com.example.learntocustomcearchwithfiltertolistview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Country> {

    List<Country> listCountry;
    Context context;
    CustomFilter filter;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Country> objects) {
        super(context, resource, objects);
        this.listCountry = objects;
        this.filter = new CustomFilter(listCountry, this);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_adapter_item_layout, null);
        Country country = listCountry.get(position);

        ImageView icon = view.findViewById(R.id.countryIcon);
        TextView name = view.findViewById(R.id.countryName);

        name.setText( country.name );
        icon.setImageDrawable(ContextCompat.getDrawable(context, country.res));

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_adapter_item_layout, null);
        Country country = listCountry.get(position);

        ImageView icon = view.findViewById(R.id.countryIcon);
        TextView name = view.findViewById(R.id.countryName);

        name.setText( country.name );
        icon.setImageDrawable(ContextCompat.getDrawable(context, country.res));

        return view;

    }

    // create and set a custom filter to listView
    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    public class CustomFilter extends Filter {

        List<Country> originalList;
        List<Country> filteredList;
        CustomAdapter adapter;

        public CustomFilter(List<Country> listCountry, CustomAdapter adapter) {
            this.originalList = new ArrayList<>(listCountry);
            this.filteredList = new ArrayList<>(listCountry);
            this.adapter = adapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            filteredList.clear();

            FilterResults filterResults = new FilterResults();
            String q = charSequence.toString().toLowerCase();

            if(q != null && q.length() > 0) {
                for(Country country : listCountry) {
                    if (country.name != null && country.name.toLowerCase().contains(q)) {
                        filteredList.add(country);
                    }
                }
            } else {
                filteredList.addAll(originalList);
            }

            filterResults.values = filteredList;
            filterResults.count = filteredList.size();

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            adapter.clear();
            adapter.addAll((List<Country>) filterResults.values);
            adapter.notifyDataSetChanged();
        }
    }
}
