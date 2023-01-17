package com.example.appwhowroteit;

import android.content.Context;
import android.hardware.input.InputManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mBookInput;
    private TextView mTitleText, mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText)findViewById(R.id.bookInput);
        mTitleText = (TextView)findViewById(R.id.titleText);
        mAuthorText = (TextView)findViewById(R.id.authorText);

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

        if(checkConnectivity()) {

            if(checkEdits(queryString)) {
                new FetchBook(mTitleText,mAuthorText).execute(queryString);
                ocultarTeclado(view);
                clearEdits();
            }

        } else {

            mAuthorText.setText("");
            mTitleText.setText(R.string.no_network);

        }

    }

}