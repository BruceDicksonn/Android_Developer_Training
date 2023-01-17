package com.example.appwhowroteit;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.ref.WeakReference;

public class BookLoader extends AsyncTaskLoader<String> {

    private String mQueryString;


    public BookLoader(@NonNull Context context, String query) {
        super(context);
        mQueryString = query;
    }



    @Nullable
    @Override
    public String loadInBackground() {

        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad(); // inicia o loadInBackground();
    }
}
