package com.example.kuba.arkanoid;

import android.media.Image;
import android.widget.ImageView;

import static com.example.kuba.arkanoid.MainThread.canvas;

public class Platform {
    private float x;
    private float y;
    private int width;
    private int height;
    private ImageView image;

    Platform(float x, float y, int width, int height, ImageView image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void display() {
        this.image.setX(x);
        this.image.setY(y);
    }

    public void update() {
        moveRight();
    }

    void moveRight() {
        this.x += 1;
    }

    void moveLeft() {
        this.x += -1;
    }

    void setImage (ImageView image) {
        image = image;
    }
}
