package com.example.kuba.arkanoid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {

    TextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        testTextView = findViewById(R.id.testTextView);

        testTextView.setText("It just works");
    }
}
