package com.example.kuba.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.media.Image;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    private Platform platform;

    ImageView platformImage;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        //platformImage.findViewById(R.id.imagePlatform);
        //platformImage.setImageResource(R.drawable.platform);
        //platformImage.setImageBitmap(R.drawable.platform);
        platform = new Platform(250, 800, 50, 20, platformImage);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {e.printStackTrace(); }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        platform.update();
    }

    @Override
    public void draw (Canvas canvas) {
        super.draw(canvas);

        platformImage.setImageResource(R.drawable.platform);
    }

    public void draw (ImageView imageView) {
        //platformImage = imageView;
        platform.setImage(imageView);
        platform.display();
    }



}
