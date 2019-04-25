package com.example.kuba.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;


// Przerzucić do MainActivity


public class GamePanel extends SurfaceView implements Runnable {
    Thread gameThread = null;
    SurfaceHolder surfaceHolder;
    boolean playing;
    boolean paused = true;
    Canvas canvas;
    Paint paint;
    long fps;
    private long timeThisFrame;
    public GamePanel(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint =  new Paint();
    }

    public void run() {
        while (playing) {
            long startFrameTime = System.currentTimeMillis();
            if (!paused) {
                update();
            }
            draw();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    public void update() {

    }

    public void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.argb(255, 26, 128, 182));
            paint.setColor(Color.argb(255, 255, 255, 255));
            // draw the paddle
            // draw the ball
            // draw the bricks
            // draw the HUD
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            // player touched the screen
            case MotionEvent.ACTION_DOWN:
                break;
            //player removed finger from the screen
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // tell the gameView resume method to execute
        GamePanel.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // tell the gameView pause method to execute
        GamePanel.pause();
    }
}
