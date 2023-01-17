package com.example.appwhowroteit;

import android.os.AsyncTask;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.ref.WeakReference;

public class FetchBook extends AsyncTask<String, Void, String> {

    private WeakReference<TextView> mTitleText;
    private WeakReference<TextView> mAuthorText;

    FetchBook(TextView titleText, TextView authorText) {
        this.mTitleText = new WeakReference<>(titleText);
        this.mAuthorText = new WeakReference<>(authorText);
    }


    @Override
    protected String doInBackground(String... strings) {

        String s = NetworkUtils.getBookInfo(strings[0]);

        return s;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {

            JSONObject jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while(i < array.length() && (authors == null && title == null)){

                JSONObject book = array.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                title = volumeInfo.getString("title");
                authors = volumeInfo.getString("authors");

                i++;

            }

            if(title != null && authors != null) {

                mTitleText.get().setText(title);
                mAuthorText.get().setText(authors);

            } else {

                mTitleText.get().setText(R.string.no_results);
                mAuthorText.get().setText("");

            }

        } catch (JSONException e) {

            mTitleText.get().setText(R.string.no_results);
            mAuthorText.get().setText("");
            e.printStackTrace();

        }

    }
}
