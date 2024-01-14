package lib;

import javafx.animation.AnimationTimer;

public abstract class GameLoopTimer extends AnimationTimer {

    private long lastFrameTimeNanos;
    private long pauseStart;
    boolean isPaused = false;
    private boolean isActive = false;
    private boolean pauseScheduled = false;
    private boolean playScheduled = false;
    private boolean restartScheduled= false;

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * pause the game
     */
    public void pause() {
        if (!isPaused) {
            pauseScheduled = true;
        }
    }

    /**
     * restart the game
     */
    public void play() {
        if (isPaused) {
            playScheduled = true;
        }
    }

    /**
     * start the game for the first time, method inherited from AnimationTimer
     */
    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

    /**
     * stop the game, method inherited from AnimationTimer
     */
    @Override
    public void stop() {
        super.stop();
        pauseStart = 0;
        isPaused = false;
        isActive = false;
        pauseScheduled = false;
        playScheduled = false;
    }

    /**
     * inherited from Animation timer, it is the method that call the tick and is not called directly by us but by JavaFX
     * @param now
     */
    @Override
    public void handle(long now){
        if (pauseScheduled) {
            pauseStart = now;
            isPaused = true;
            pauseScheduled = false;
        }

        if (playScheduled) {
            isPaused = false;
            playScheduled = false;
        }

        if (restartScheduled) {
            isPaused = false;
            restartScheduled = false;
        }

        if (!isPaused) {
            float secondsSinceLastFrame = (float) ((now - lastFrameTimeNanos) / 1e9);
            lastFrameTimeNanos = now;
            tick(secondsSinceLastFrame);
        }

    }

    public abstract void tick(float secondsSinceLastFrame);



}
