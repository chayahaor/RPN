package com.example.rpn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> resultsRecords = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        Button computeButton = findViewById(R.id.buttonCompute);
        computeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        });

        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView tvInput = findViewById(R.id.textInput);
                tvInput.setText("");
            }
        });
    }



    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void compute() {
        EditText text = findViewById(R.id.textInput);
        String expression = text.getText().toString();
        boolean flagError = false;
        Stack<Double> stackExpression = new Stack<>();
        double newNum = 0.0;
        String[] splitExpression = expression.split("\\s+");
        for (String s : splitExpression) {
            if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") ) {
                try {
                    double num1 = stackExpression.pop();
                    double num2 = stackExpression.pop();

                    switch (s) {

                        case "-":
                            newNum = num2 - num1;
                            break;
                        case "+":
                            newNum = num2 + num1;
                            break;
                        case "/":
                            if (num1 != 0) {
                                newNum = num2 / num1;
                            } else {
                                flagError = true;
                            }
                            break;
                        case "*":
                            newNum = num2 * num1;
                            break;
                    }

                    stackExpression.push(newNum);


                } catch (Exception e) {
                    flagError = true;
                }

            } else {
                try {
                    Double num = Double.parseDouble(s);
                    stackExpression.push(num);
                } catch (Exception e) {
                    flagError = true;
                }
            }

        }
        TextView resultsBox = findViewById(R.id.result);
        String result = "";
        try {
            result = String.format("%.2f", stackExpression.pop());
        } catch (Exception e) {
            flagError = true;
        }
        if (!flagError && stackExpression.empty()) {
            resultsBox.setText(result);
            resultsRecords.add(expression + " = " + result);

        } else {
            resultsBox.setText(R.string.error_message);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //set color so that visible
        for(int i = 0; i<menu.size(); i++ ){
            MenuItem menuItem = menu.getItem(i);
            SpannableString spannable = new SpannableString(
                    menu.getItem(i).getTitle().toString()
            );
            spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.secondaryDarkColor)),
                    0, spannable.length(),0);
            menuItem.setTitle(spannable);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            // User chose the "Settings" item, show the app settings UI...
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId()==R.id.otherRPN){
            // User chose the "Other RPN" item, show the previous RPN expressions...
            Intent intent = new Intent(this, PrevRPN.class);
            startActivity(intent);
            return true;
        }
        else if (item.getItemId()==R.id.about_rpn){
            // User chose the "About RPN" item, show about RPN...
            Intent intent = new Intent(this, AboutRPN.class);
            startActivity(intent);
            return true;
        }
        else {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            return super.onOptionsItemSelected(item);
        }
    }


}