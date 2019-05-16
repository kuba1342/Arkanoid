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

    boolean running;
    static final int maxFPS = 30;
    private double averageFPS;

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

        long startTime;
        long timeMillis = 1000/maxFPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/maxFPS;

        running = true;

        while(running) {
            startTime = System.nanoTime();
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0){}
                    //this.sleep(waitTime);
            } catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == maxFPS) {
                averageFPS = 1000 / (totalTime / frameCount) / 1000000;
                frameCount = 0;
                totalTime = 0;
            }

            playerPlatform.display(); // pętla się wysypuje
        }
    }
}
