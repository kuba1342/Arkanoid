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
    public static int yellows;
    public static int greens;
    public static int pinks;
    public static int greyCounter = 0;

    public Brick(int row, int column, int width, int height, int redLimit, int purpleLimit, int greyLimit,
                 int yellowLimit, int pinkLimit, int greenLimit) {
        isVisible = true;
        int padding = 1;
        rect = new RectF(column * width + padding,
                row * height + padding,
                column * width + width - padding,
                row * height + height - padding);
        generateStatus(redLimit, purpleLimit, greyLimit, yellowLimit, pinkLimit, greenLimit);
        setScore();
    }

    public RectF getRect() {
        return this.rect;
    }

    public void generateStatus(int checkRed, int checkPurple, int checkGrey, int checkYellow, int checkPinks,
                               int checkGreens) {
        int random = (int) (Math.random() * 9 + 1);
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
                greyCounter += 1;
            }
        }
        if (random == 4) {
            if (yellows < checkYellow) {
                status = "yellow";
                yellows += 1;
            }
        }
        if (random == 5) {
            if (pinks < checkPinks) {
                status = "pink";
                pinks += 1;
            }
        }

        if (random == 6) {
            if (greens < checkGreens) {
                status = "green";
                greens += 1;
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
        }
        else if (status == "yellow") {
            score = 75;
        }
        else if (status == "pinks") {
            score = 65;
        }
        else if(status == "greens") {
            score = 55;
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
