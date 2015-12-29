package com.brycevalero.www.elisgames.fireworks;

/**
 * Created by bryce on 12/7/2015.
 */
import com.brycevalero.www.elisgames.R;
import com.brycevalero.www.elisgames.fireworks.explosion.Explosion;
import com.brycevalero.www.elisgames.fireworks.sound.SoundFX;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.media.MediaPlayer;


public class FireworksView extends SurfaceView implements SurfaceHolder.Callback
{
    private static final String TAG = FireworksView.class.getSimpleName();
    private Context context;

    private static final int EXPLOSION_SIZE = 100;//number of particl

    private FireworksThread thread;
    private Explosion[] explosions;
    private int explosionIndex = 0;//used to iterate through explosions array.

    // the fps to be displayed
    private String avgFps;
    public void setAvgFps(String avgFps) {
        this.avgFps = avgFps;
    }

    public FireworksView(Context context) {
        super(context);
        this.context = context;

        explosions = new Explosion[10];
        // adding the callback (this) to the surface holder to intercept events
        getHolder().addCallback(this);

        // make the GamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // create the game loop thread
        thread = new FireworksThread(getHolder(), this);

        // at this point the surface is created and
        // we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
        // tell the thread to shut down and wait for it to finish
        // this is a clean shutdown
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            SoundFX.playExplosionSound(context);

            // handle touch
            // check if explosion is null or if it is still active
            for(int i = 0; i < explosions.length; i++) {
                if (explosions[explosionIndex] == null || explosions[explosionIndex].getState() == Explosion.STATE_DEAD) {
                    explosions[explosionIndex] = new Explosion(EXPLOSION_SIZE, (int) event.getX(), (int) event.getY());
                }
            }

            nextExplosionIndex();
        }
        return true;
    }

    private void nextExplosionIndex(){
        System.out.println("Explosion Number: " + explosionIndex);

        explosionIndex++;
        if(explosionIndex == explosions.length){
            explosionIndex = 0;
        }
    }

    public void render(Canvas canvas) {

        if(canvas != null) {
            canvas.drawColor(Color.BLACK);

            // render explosions
            for(int i = 0; i < explosions.length; i++) {
                if (explosions[i] != null) {
                    explosions[i].draw(canvas);
                }
            }

            // display fps
            displayFps(canvas, avgFps);

            // display border
            /*Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawLines(new float[]{
                    0, 0, canvas.getWidth() - 1, 0,
                    canvas.getWidth() - 1, 0, canvas.getWidth() - 1, canvas.getHeight() - 1,
                    canvas.getWidth() - 1, canvas.getHeight() - 1, 0, canvas.getHeight() - 1,
                    0, canvas.getHeight() - 1, 0, 0
            }, paint);*/
        }
    }

    /**
     * This is the game update method. It iterates through all the objects
     * and calls their update method if they have one or calls specific
     * engine's update method.
     */
    public void update() {
        // update explosions
        for(int i = 0; i < explosions.length; i++) {
            if (explosions[i] != null && explosions[i].isAlive()) {
                explosions[i].update(getHolder().getSurfaceFrame());
            }
        }
    }

    private void displayFps(Canvas canvas, String fps) {
        if (canvas != null && fps != null) {
            Paint paint = new Paint();
            paint.setARGB(255, 255, 255, 255);
            canvas.drawText(fps, this.getWidth() - 50, 20, paint);
        }
    }
}
