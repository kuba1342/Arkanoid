package com.example.kuba.arkanoid;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {

    Display display;

    ImageButton rightButton;
    ImageButton leftButton;

    Platform playerPlatform;
    int platformSize;
    ImageView platformImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        setContentView(R.layout.activity_level1);

        rightButton = findViewById(R.id.rightButton);
        leftButton = findViewById(R.id.leftButton);

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerPlatform.moveLeft();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerPlatform.moveRight();
            }
        });

        platformImage = findViewById(R.id.imagePlatform);
        platformImage.setImageResource(R.drawable.platform);
        platformSize = 50;
        playerPlatform = new Platform(250, 800, platformSize, 20, platformImage); //Dodać pozycje na stałe
        //while(true) {
            playerPlatform.display(); // pętla się wysypuje
        //}
    }
}
