package com.example.rpn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutRPN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_rpn);
        TextView resultsBox = findViewById(R.id.about);
        resultsBox.setText("Hey there! Is this working"); //TODO: remove before submitting

    }
}