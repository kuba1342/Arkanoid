package com.example.kuba.arkanoid;

import android.graphics.RectF;

public class Brick {
    private RectF rect;
    private boolean isVisible;
    private int score;
    private String status;
    public static int reds;
    public static int purples;
    public static int greys;

    public Brick(int row, int column, int width, int height, int redLimit, int purpleLimit, int greyLimit) {
        isVisible = true;
        int padding = 1;
        rect = new RectF(column * width + padding,
                row * height + padding,
                column * width + width - padding,
                row * height + height - padding);
        generateStatus(redLimit, purpleLimit, greyLimit);
        setScore();
    }

    public RectF getRect() {
        return this.rect;
    }

    public void generateStatus(int checkRed, int checkPurple, int checkGrey) {
        int random = (int) (Math.random() * 3 + 1);
        if (random == 1) {
            if (reds < checkRed) {
                status = "red";
                reds += 1;
            }
        }
        if (random == 2) {
            if (purples < checkPurple) {
                status = "purple";
                purples += 1;
            }
        }
        if (random == 3) {
            if (greys < checkGrey) {
                status = "grey";
                greys += 1;
            }
        }

    }

    private void setScore() {
        if (status == "red") {
            score = 100;
        } else if (status == "purple") {
            score = 90;
        } else if (status == "grey") {
            score = 0;
        } else {
            score = 45;
        }
    }

    public void setStatus(String color) {
        status = color;
    }

    public String getStatus() {
        return status;
    }

    public void setInvisible() {
        isVisible = false;
    }

    public boolean getVisibility() {
        return isVisible;
    }

    public int getScore() {
        return score;
    }
}
