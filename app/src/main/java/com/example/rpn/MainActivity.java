package com.example.rpn;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> resultsRecords=new ArrayList<>();
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
            if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*")) {
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
        Button resultButton = findViewById(R.id.result);
        String result = "";
        try {
            result = String.format("%.2f", stackExpression.pop());
        } catch (Exception e) {
            flagError=true;
        }
        if (!flagError && stackExpression.empty()) {
            resultButton.setText(result);
            resultsRecords.add(result);

        } else {
            resultButton.setText(R.string.error_message);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}