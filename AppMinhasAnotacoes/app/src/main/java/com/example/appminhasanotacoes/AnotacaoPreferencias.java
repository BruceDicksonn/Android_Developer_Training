package com.example.appminhasanotacoes;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {

    public Context context;
    static final String ANOTACAO_PREFERENCE = "com.example.minhasanotacoes";

    public AnotacaoPreferencias(Context context){
       this.context = context;
    }

    public void salvarAnotacao(String anotacao){
        SharedPreferences preferences = context.getSharedPreferences(ANOTACAO_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("anotacao", anotacao);
        editor.commit();

    }

    public String recuperarAnotacao(){
        SharedPreferences preferences = context.getSharedPreferences(ANOTACAO_PREFERENCE, Context.MODE_PRIVATE);
        String anotacao = preferences.getString("anotacao","");
        return anotacao;
    }

}
