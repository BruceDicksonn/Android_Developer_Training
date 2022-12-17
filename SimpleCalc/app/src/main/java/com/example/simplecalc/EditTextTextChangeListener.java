package com.example.simplecalc;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class EditTextTextChangeListener implements TextWatcher {

    private Context context;
    private EditText editText;

    public EditTextTextChangeListener(Context context,EditText editText) {
        super();
        this.context = context;
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        //MainActivity.fieldsAreEmpty();
    }



}
