package com.example.kuba.arkanoid;

import android.graphics.Point;
import android.graphics.RectF;
import android.media.Image;
import android.view.Display;
import android.widget.ImageView;

public class Platform {
    private float x;
    private float y;
    private int width;
    private int height;
    private ImageView image;
    private float platformSpeed;
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    private int screenWidth;

    private RectF rect;

    private int platformMoving = STOPPED;

    Platform(float x, float y, int width, int height, ImageView image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    Platform(float x, float y, int width, int height) {
        // x = screenX / 2;
        // y = screenY - 20;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //rect = new RectF(x, y, x + width, this.height);
        rect = new RectF(x, y, x + width, y + height);
        platformSpeed = 350;
    }

    Platform(float x, float y, int width, int height, int screenWidth) {
        // x = screenX / 2;
        // y = screenY - 20;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //rect = new RectF(x, y, x + width, this.height);
        rect = new RectF(x, y, x + width, y + height);
        platformSpeed = 350;
        this.screenWidth = screenWidth;
    }

    public RectF getRect() {
        return rect;
    }

    public void setMovementState(int state) {
        platformMoving = state;
    }

    public void display() {
        this.image.setX(x);
        this.image.setY(y);
    }

    public void update(long fps) {
        if (platformMoving == LEFT && (rect.centerX() - width / 2 > 0)) {
            x = x - platformSpeed / fps;
        }

        if (platformMoving == RIGHT && (rect.centerX() + width / 2 < screenWidth)) {
            x = x + platformSpeed / fps;
        }

        rect.left = x;
        rect.right = x + width;
    }

    void moveRight() {
        this.x += 1;
    }

    void moveLeft() {
        this.x += -1;
    }

    void setImage(ImageView image) {
        image = image;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int value) {
        this.width += value;
    }
}
