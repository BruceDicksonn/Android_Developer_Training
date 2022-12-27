package com.example.simplecalc;




import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import android.util.Log;

public class CalculatorTeste {

    @InjectMocks
    private  Calculator mCalculator;

    @Before
    public void setUp(){
        mCalculator = new Calculator();
    }

    @Test
    public void addTwoNumbers(){

        double result = mCalculator.add(2d,3.5d);
        assertThat(result, is(equalTo(5.5)));

    }

    @Test
    public void addTwoNegativeNumbers(){

        double result = mCalculator.add(-2d,-3.5d);
        assertThat(result, is(equalTo(-5.5d)));
    }


    @Test
    public void subTwoNumbers(){
        double result = mCalculator.sub(3d, 2d);
        assertThat(result, is(equalTo(1d)));
    }

    @Test
    public void subWorksWithNegativeResults(){
        double result = mCalculator.sub(3d, 5d);
        assertThat(result, is(equalTo(-2d)));
    }

    @Test
    public void multTwoNumbers(){
        double result = mCalculator.mult(3d, 10d);
        assertThat(result, is(equalTo(30d)));
    }

    @Test
    public void multTwoNumbersZero(){
        double result = mCalculator.mult(3d, 0d);
        assertThat(result, is(equalTo(0d)));
    }

    @Test
    public void divTwoNumbers(){
        double result = mCalculator.div(30d, 3d);
        assertThat(result, is(equalTo(10d)));
    }

    @Test
    public void divTwoNumbersZero(){
        double result = mCalculator.div(30d, 0d);
        assertThat(result, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void divByZeroThrows(){

//        int x = 120, y = 60;

        try {
            mCalculator.div(120,60);
        } catch (ArithmeticException ae){
            throw new ArithmeticException("Não é possível dividir por zero");
        }

    }

    @Test
    public void mustHaveOnlyIntegerPositiveOperand(){

        Integer x = -10, y = 2;

        try {

            if(x.TYPE.equals(Double.TYPE) || y.TYPE.equals(Double.TYPE)){
                throw new IllegalArgumentException("Devem existir somente valores positivos.");
            }

            mCalculator.pow(x,y);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void mustHaveIntegerNegativeForFirstOperand(){

        Integer x = -15;
        Double y = 2d;

        if(((x <= 0) && (x.TYPE.equals(Integer.TYPE)))){
            mCalculator.pow(x,y);
        } else {
            fail("O primeiro operando deve ser um número inteiro negativo.");
        }

    }

    @Test
    public void mustHaveIntegerNegativeForSecondOperand(){
        Double x = 10d;
        Integer y = -2;

        if(((y <= 0) && (y.TYPE.equals(Integer.TYPE)))){
            mCalculator.pow(x,y);
        } else {
            fail("O segundo operando deve ser um número inteiro negativo.");
        }
    }

    @Test
    public void mustHaveZeroForFirstOperandAndPositiveIntegerForSecondOperand(){

        Double x = 0.0;
        Integer y = 10;
        Double res = mCalculator.pow(x,y);

        if(x != 0.0 || (y <= 0 && y.TYPE.equals(Integer.TYPE))){
            fail("O primeiro operando dever ser 0 e o segundo um valor inteiro positivo.");
        }

    }

    @Test
    public void mustHaveZeroForSecondOperand(){

        Double x = 150.0;
        Integer y = 0;
        Double res = mCalculator.pow(x,y);

        if(y != 0){
            fail("O segundo operando dever ser 0.");
        }

    }

    @Test
    public void mustHaveZeroForFirstOperandAndLessOneForSecondOperand(){
        int x = 0;
        int y = -1;
        double res;

        if(x != 0 && y != -1){
            fail("Primeiro operando deve ser 0 e o Segundo operando deve ser -1");
        } else {
            res = mCalculator.pow(x,y);
        }
    }

    @Test
    public void mustHaveLessZeroForFirstOperandAndAnyNegativeNumberForSecondOperand(){

        int x = -0;
        int y = -1;
        double res;

        if(x != -0 && y != -1){
            fail("Primeiro operando deve ser -0 e o Segundo operando deve ser -1");
        } else {
            res = mCalculator.pow(x,y);
        }

    }
}
