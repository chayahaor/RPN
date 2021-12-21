package com.example.rpn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        resultsBox.setText(results.toString());
    }
}