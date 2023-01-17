package com.example.appwhowroteit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText mBookInput;
    private TextView mTitleText, mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);

        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }

    }

    public void ocultarTeclado(View view){

        // Objeto responsável pelo método de digitação do teclado, com ele podemos gerenciá-lo.
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if (inputManager != null) {

            // passamos o token do view que está disparando o metodo e a constante flag de InputMethodManager
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void clearEdits(){
        mTitleText.setText("");
        mAuthorText.setText("");
    }

    public boolean checkEdits(String queryString){

        if(queryString.length() == 0){
            mAuthorText.setText("");
            mTitleText.setText(R.string.no_search_term);
            return false;
        }

        return true;

    }

    public boolean checkConnectivity(){ // Método para checarmos a conexão atual do dispositivo

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;

        if(manager != null) {
            info = manager.getActiveNetworkInfo();
        }

        if(info != null && info.isConnected()) {
            return true;
        }

        return false;
    }

    public void searchBooks(View view) {

        String queryString = mBookInput.getText().toString();

        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", queryString);

        getSupportLoaderManager().restartLoader(0,queryBundle,this);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {

        String query = "";

        if(bundle != null) {

            query = bundle.getString("queryString");

        }

        return new BookLoader(this,query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
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

            mTitleText.setText(title);
            mAuthorText.setText(authors);

        } catch (JSONException e) {

            e.printStackTrace();

        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}