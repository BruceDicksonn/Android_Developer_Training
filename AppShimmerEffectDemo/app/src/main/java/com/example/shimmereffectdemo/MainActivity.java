package com.example.shimmereffectdemo;

import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NestedScrollView nestedScrollView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ShimmerFrameLayout shimmerFrameLayout;
    ArrayList<MainData> dataArrayList = new ArrayList<MainData>();
    MainAdapter adapter;

    int page = 1, limit = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initComponents(){

        nestedScrollView     = findViewById(R.id.scroll_view);
        recyclerView         = findViewById(R.id.recycler_view);
        progressBar          = findViewById(R.id.progress_bar);
        shimmerFrameLayout   = findViewById(R.id.shimmer_layout);

        adapter = new MainAdapter(this, dataArrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getData();

        shimmerFrameLayout.startShimmer();

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView view, int x, int y, int oldX, int oldY) {

                if(y == view.getChildAt(0).getMeasuredHeight() - view.getMeasuredHeight()){
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getData();
                }

            }
        });

    }

    private void getData() {

        String sUrl = "https://picsum.photos/v2/list?page="+ page + "&limit=" + limit;

        StringRequest stringRequest = new StringRequest(sUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response != null){

                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                    shimmerFrameLayout.stopShimmer();
                    shimmerFrameLayout.setVisibility(View.GONE);

                    try {

                        JSONArray jsonArray = new JSONArray(response);
                        parseArray(jsonArray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);

    }

    private void parseArray(JSONArray jsonArray) {

        for(int i=0; i < jsonArray.length(); i++){

            try {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MainData data = new MainData();

                data.setImage(jsonObject.getString("download_url"));
                data.setName(jsonObject.getString("author"));
                data.setUrl(jsonObject.getString("url"));

                dataArrayList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            adapter = new MainAdapter(this, dataArrayList);
            recyclerView.setAdapter(adapter);


        }

    }
}