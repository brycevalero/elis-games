package com.brycevalero.www.elisgames.clouds;

/**
 * Created by bryce on 12/7/2015.
 */
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

import com.brycevalero.www.elisgames.clouds.frog.Frog;
import com.brycevalero.www.elisgames.clouds.sound.SoundFX;
import com.brycevalero.www.elisgames.game.elements.Background;
import com.brycevalero.www.elisgames.R;


public class CloudsView extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 720;
    public static final int HEIGHT = 1280;
    private CloudsThread thread;
    private Background bg;
    private Background bgTop;
    private Bitmap frogFloatingImg, frogFallingImg;
    private Frog[] frogs;
    private MediaPlayer player;

    public CloudsView(Context context)
    {
        super(context);
        setKeepScreenOn(true);
        frogs = new Frog[10];

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new CloudsThread(getHolder(), this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry)
        {
            try{thread.setRunning(false);
                thread.join();

            }catch(InterruptedException e){e.printStackTrace();}
            retry = false;
        }

        player.stop();
        player.release();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        bg = new Background(this.getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.clouds_bottom));
        bgTop = new Background(this.getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.clouds_top));

        frogFloatingImg = BitmapFactory.decodeResource(getResources(), R.drawable.frogfloating);
        frogFallingImg = BitmapFactory.decodeResource(getResources(), R.drawable.frogfalling);

        for(int i = 0; i < frogs.length; i++){
            frogs[i] = new Frog(this.getContext(), frogFloatingImg);
            frogs[i].setFloatingImg(frogFloatingImg);
            frogs[i].setFallingImg(frogFallingImg);
        }

        bg.setVerticalVector(3);
        bgTop.setVerticalVector(7);

        bg.setObjectH(CloudsView.HEIGHT);
        bgTop.setObjectH(CloudsView.HEIGHT);

        bg.setObjectW(CloudsView.WIDTH);
        bgTop.setObjectW(CloudsView.WIDTH);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

        player= MediaPlayer.create(this.getContext(), R.raw.slow_bells);
        player.setLooping(true);
        player.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        System.out.println("---------------------------------");
        System.out.println(event.getX());
        System.out.println(event.getY());
        System.out.println("---------------------------------");

        for(int i = 0; i < frogs.length; i++){
            if(frogs[i].inBounds(event.getX(), event.getY()))
            {
                frogs[i].setCurrentState(Frog.FALLING);
                frogs[i].setImage(frogFallingImg);
                SoundFX.playImpactSound(this.getContext());
                SoundFX.playScreamSound(this.getContext());
            }
        }

        return super.onTouchEvent(event);
    }

    public void update()
    {
        bg.update();
        bgTop.update();

        int idleCount = 0;
        for(int i = 0; i < frogs.length; i++){
            frogs[i].update();

            if(frogs[i].getCurrentState() == Frog.IDLE)
            {
                idleCount++;
            }
            if(frogs[i].getObjectY() < -400) {
                frogs[i].resetFrog();
            }
            if(frogs[i].getObjectY() > 2000){
                frogs[i].idleFrog();

            }
        }
        if(idleCount == frogs.length){
            for(int i = 0; i < frogs.length; i++) {
                frogs[i].setCurrentState(Frog.FLOATING);
            }
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleFactorX = getWidth()/WIDTH;
        final float scaleFactorY = getHeight()/HEIGHT;
        if(canvas!=null)
        {

            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            bgTop.draw(canvas);

            for(int i = 0; i < frogs.length; i++){
                frogs[i].draw(canvas);
            }
            canvas.restoreToCount(savedState);
        }
    }
}
