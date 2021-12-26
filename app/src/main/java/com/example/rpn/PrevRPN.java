package com.example.rpn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PrevRPN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_rpn);
        ArrayList<String> results = MainActivity.resultsRecords;
        TextView resultsBox = findViewById(R.id.textView);
        String stringResults="";
        for (int i=0;i<results.size(); i++){
            stringResults=stringResults+"\n"+ (i + 1) + ". "  + results.get(i);
        }
        resultsBox.setText(stringResults);
        resultsBox.setMovementMethod(new ScrollingMovementMethod());

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.resultsRecords.clear();
                String stringResults="";
                resultsBox.setText(stringResults);
            }
        });
    }
}