package com.example.kuba.arkanoid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    Button startButton;
    Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        startButton = (Button) findViewById(R.id.startButton);
        exitButton = findViewById(R.id.exitButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    void startGame() {
        Intent intent = new Intent(this, Level1.class);
        startActivity(intent);
    }
}
