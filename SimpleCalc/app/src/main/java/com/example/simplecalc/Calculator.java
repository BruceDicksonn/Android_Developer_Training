package com.example.simplecalc;

import android.util.Log;

public class Calculator {

    public static enum Operator {
        ADD, SUB, MULT, DIV, POW
    }

    public double add(double x, double y){
        return x + y;
    }

    public double sub(double x, double y){
        return x - y;
    }

    public double mult(double x, double y){
        return x * y;
    }

    public double div(double x, double y){
        return x / y;
    }

    public double div(int x, int y){
        return x / y;
    }

    public double pow(double x, double y){
        return Math.pow(x,y);
    }

}
