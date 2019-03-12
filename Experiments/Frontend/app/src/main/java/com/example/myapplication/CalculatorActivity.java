package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    //Creating buttons and TextViews for the calc
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button add;
    private Button subtract;
    private Button divide;
    private Button multitply;
    private Button clear;
    private Button equal;

    private TextView control;
    private TextView result;

    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLY = '*';
    private final char DIVIDE = '/';
    private final char EQUAL = '0';

    private double var1 = Double.NaN;
    private double var2 = Double.NaN;

    private char ACTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_layout);

        initCalcView();

        zero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "0");
            }
        });

        one.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "1");
            }
        });

        two.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "2");
            }
        });

        three.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "3");
            }
        });

        four.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "4");
            }
        });

        five.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "5");
            }
        });

        six.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText(control.getText().toString() + "9");
            }
        });

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                control.setText("");
                result.setText("");
                var1 = Double.NaN;
                var2 = Double.NaN;

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = ADDITION;
                result.setText(String.valueOf(var1) + "+");
                control.setText(null);
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = SUBTRACTION;
                result.setText(String.valueOf(var1) + "-");
                control.setText(null);
            }
        });

        multitply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = MULTIPLY;
                result.setText(String.valueOf(var1) + "*");
                control.setText(null);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = DIVIDE;
                result.setText(String.valueOf(var1) + "/");
                control.setText(null);
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compute();
                ACTION = EQUAL;
                result.setText(result.getText().toString() + String.valueOf(var2) + "=" +
                        String.valueOf(var1));
                control.setText(null);
            }
        });
    }

    private void initCalcView(){
        zero = (Button) findViewById(R.id.btnZero);
        one = (Button) findViewById(R.id.btnOne);
        two = (Button) findViewById(R.id.btnTwo);
        three = (Button) findViewById(R.id.btnThree);
        four = (Button) findViewById(R.id.btnFour);
        five = (Button) findViewById(R.id.btnFive);
        six = (Button) findViewById(R.id.btnSix);
        seven = (Button) findViewById(R.id.btnSeven);
        eight = (Button) findViewById(R.id.btnEight);
        nine = (Button) findViewById(R.id.btnNine);
        add = (Button) findViewById(R.id.btnAdd);
        subtract = (Button) findViewById(R.id.btnSubstract);
        divide = (Button) findViewById(R.id.btnDivide);
        multitply = (Button) findViewById(R.id.btnMultiply);
        clear = (Button) findViewById(R.id.btnClear);
        equal = (Button) findViewById(R.id.btnEqual);
        control = (TextView) findViewById(R.id.tvControl);
        result = (TextView) findViewById(R.id.tvResult);
        equal = (Button) findViewById(R.id.btnEqual);
    }

    private void compute(){
        if(!Double.isNaN(var1)){
            var2 = Double.parseDouble(control.getText().toString());
            if(ACTION == ADDITION){
                var1 += var2;
            }
            else if(ACTION == SUBTRACTION){
                var1 -= var2;
            }
            else if(ACTION == MULTIPLY){
                var1 *= var2;
            }
            else if(ACTION == DIVIDE){
                var1 /= var2;
            }
        }
        else{
            var1 = Double.parseDouble(control.getText().toString());
        }
    }
}