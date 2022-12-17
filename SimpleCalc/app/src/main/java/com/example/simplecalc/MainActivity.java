package com.example.simplecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText n1,n2;
    TextView result;
    Button a, s, m, d, p;
    Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();

    }

    private void addTextWatcher(EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Log.e("MainActivity", String.valueOf(charSequence));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*
                *
                *   NOTA: caso queira ter referência a componentes da classe atual, é importante
                *   passar o contexto do componente, pois, dentro dos eventos, um novo escopo é
                *   criado. O escopo desses eventos não reconhecem nada externo a ele.
                *
                * */
                boolean n1 = MainActivity.this.n1.getText().toString().isEmpty();
                boolean n2 = MainActivity.this.n2.getText().toString().isEmpty();

                if(!n1 && !n2){
                    enableButtons(true, true);
                    //Log.e("MainActivity", String.valueOf(editText.getId())+ "\t" + editable.toString());
                } else {
                    enableButtons(false,false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void compute(Calculator.Operator field){

        double x = getOperand(n1);
        double y = getOperand(n2);
        double r = 0.0;

        switch (field) {
            case ADD:
                r = calculator.add(x,y);
            break;
            case SUB:
                r = calculator.sub(x,y);
            break;
            case MULT:
                r = calculator.mult(x,y);
            break;
            case DIV:
                r = calculator.div(x,y);
            break;
            case POW:
                r = calculator.pow(x,y);
            break;
        }

        result.setText(String.valueOf(r));

    }

    private double getOperand(EditText view){
        return Double.parseDouble(getOperandText(view));
    }

    private String getOperandText(EditText view){
        return view.getText().toString();
    }

    private void enableButtons(boolean n1IsFill, boolean n2IsFill){

        boolean res = (n1IsFill && n2IsFill) ? true:false;

        a.setEnabled(res);
        s.setEnabled(res);
        m.setEnabled(res);
        d.setEnabled(res);
        p.setEnabled(res);

    }

    View.OnClickListener onAdd = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                compute(Calculator.Operator.ADD);
            } catch (IllegalArgumentException iae) {
                Log.e("MainActivity", iae.getMessage());
            }
        }
    };

    View.OnClickListener onSub = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                compute(Calculator.Operator.SUB);
            } catch (IllegalArgumentException iae) {
                Log.e("MainActivity", iae.getMessage());
            }
        }
    };

    View.OnClickListener onMult = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                compute(Calculator.Operator.MULT);
            } catch (IllegalArgumentException iae) {
                Log.e("MainActivity", iae.getMessage());
            }
        }
    };

    View.OnClickListener onDiv = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                compute(Calculator.Operator.DIV);
            } catch (IllegalArgumentException iae) {
                Log.e("MainActivity", iae.getMessage());
            }
        }
    };

    View.OnClickListener onPow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                compute(Calculator.Operator.POW);
            } catch (IllegalArgumentException iae) {
                Log.e("MainActivity", iae.getMessage());
            }
        }
    };


    public void initComponents(){

        n1 = findViewById(R.id.edit1);
        n2 = findViewById(R.id.edit2);

        addTextWatcher(n1);
        addTextWatcher(n2);

        a = findViewById(R.id.button_add);
        a.setOnClickListener(onAdd);

        s = findViewById(R.id.button_sub);
        s.setOnClickListener(onSub);

        m = findViewById(R.id.button_mult);
        m.setOnClickListener(onMult);

        d = findViewById(R.id.button_div);
        d.setOnClickListener(onDiv);

        p = findViewById(R.id.button_pow);
        p.setOnClickListener(onPow);

        enableButtons(false, false);

        result = findViewById(R.id.resultado);

        calculator = new Calculator();

    }
}