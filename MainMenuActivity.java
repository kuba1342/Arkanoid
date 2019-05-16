package com.example.kuba.arkanoid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainMenuActivity extends Activity {

    GamePanel gamePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gamePanel = new GamePanel(this);
        setContentView(gamePanel);
    }

    class GamePanel extends SurfaceView implements Runnable {
        Thread gameThread = null;
        SurfaceHolder surfaceHolder;
        boolean playing;
        boolean paused = true;
        Canvas canvas;
        Paint paint;
        long fps;
        private long timeThisFrame;

        int screenWidth;
        int screenHeight;

        Platform platform;
        Ball ball;

        Brick[] bricks = new Brick[200];
        int numBricks = 0;

        public GamePanel(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint = new Paint();
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            screenWidth = size.x;
            screenHeight = size.y;

            platform = new Platform(screenWidth / 2 - 65, screenHeight - 90, 130, 20);
            ball = new Ball(screenWidth, screenHeight);

            createBricksAndRestart();
        }

        public void createBricksAndRestart() {
            // put ball back to the start
            ball.reset(screenWidth, screenHeight);

            int brickWidth = screenWidth / 8;
            int brickHeight = screenHeight / 10;

            // build a wall
            numBricks = 0;

            for (int column = 0; column < 8; column++) {
                for (int row = 0; row < 3; row++) {
                    bricks[numBricks] = new Brick(row, column, brickWidth, brickHeight);
                    numBricks++;
                }
            }
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
            platform.update(fps);
            // collisions - ball and brick
            for(int i = 0; i < numBricks; i++) {
                if(bricks[i].getVisibility()) {
                    if(RectF.intersects(bricks[i].getRect(), ball.getRect())) {
                        bricks[i].setInvisible();
                        ball.reverseYVelocity();
                    }
                }
            }

            // collisions - ball and platform
            if(RectF.intersects(platform.getRect(), ball.getRect())) {
                ball.setRandomXVelocity();
                ball.reverseYVelocity();
                ball.clearObstacleY(platform.getRect().top - 2);
            }

            // collisions - bottom of the screen
            if(ball.getRect().bottom > screenHeight) {
                ball.reverseYVelocity();
                ball.clearObstacleY(screenHeight - 2);
                // lose life point...
                // check if life points still available...
            }

            // collisions - top of the screen
            if(ball.getRect().top < 0) {
                ball.reverseYVelocity();
                ball.clearObstacleY(12);
            }

            // collisions - right wall
            if(ball.getRect().right > screenWidth - 10) {
                ball.reverseXVelocity();
                ball.clearObstacleX(screenWidth - 22);
            }

            // collisions - left wall
            if(ball.getRect().left < 0) {
                ball.reverseXVelocity();
                ball.clearObstacleX(2);
            }

            ball.update(fps);
        }

        public void draw() {
            if (surfaceHolder.getSurface().isValid()) {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.argb(255, 26, 128, 182));
                paint.setColor(Color.argb(255, 255, 255, 255));
                // draw the paddle
                canvas.drawRect(platform.getRect(), paint);
                // draw the ball
                canvas.drawRect(ball.getRect(), paint);
                // draw the bricks
                // change color
                paint.setColor(Color.argb(255, 249, 129, 0));
                // draw bricks if visible
                for(int i = 0; i < numBricks; i++) {
                    if(bricks[i].getVisibility()) {
                        canvas.drawRect(bricks[i].getRect(), paint);
                    }
                }
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
                    paused = false;
                    if (motionEvent.getX() > screenWidth / 2) {
                        platform.setMovementState(platform.RIGHT);
                    } else {
                        platform.setMovementState(platform.LEFT);
                    }
                    break;
                //player removed finger from the screen
                case MotionEvent.ACTION_UP:
                    platform.setMovementState(platform.STOPPED);
                    break;
            }
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // tell the gameView resume method to execute
        gamePanel.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // tell the gameView pause method to execute
        gamePanel.pause();
    }
}
