package com.example.kuba.arkanoid;

public class Powerup {
    Platform player;
    Ball ball;

    public Powerup(Platform platform, Ball ball) {
        this.player = platform;
        this.ball = ball;
    }

    public Powerup() {}

    public void generatePowerup(Platform platform, Ball ball) {
        int random = (int) (Math.random() * 3 + 1);
        if (random == 1) {
            increasePlatformWidth(platform);
        }
        if (random == 2) {
            increaseBallSpeed(ball);
        }
        if (random == 3) {
            decreasePlatformWidth(platform);
        }
    }

    private void increasePlatformWidth(Platform platform) {
        platform.setWidth(platform.getWidth() + 10);
    }

    private void increaseBallSpeed(Ball ball) {
        ball.setXVelocity(ball.getXVelocity() + 10);
        ball.setYVelocity(ball.getYVelocity() + 10);
    }

    private void decreasePlatformWidth(Platform platform) {
        platform.setWidth(platform.getWidth() - 10);
    }
}
