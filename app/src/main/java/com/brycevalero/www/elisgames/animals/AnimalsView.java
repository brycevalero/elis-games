package com.brycevalero.www.elisgames.animals;

/**
 * Created by bryce on 12/7/2015.
 */
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.brycevalero.www.elisgames.game.elements.Background;
import com.brycevalero.www.elisgames.R;


public class AnimalsView extends SurfaceView implements SurfaceHolder.Callback
{
    public static final int WIDTH = 720;
    public static final int HEIGHT = 1280;
    private AnimalsThread thread;
    private Background bg;
    private Background bgTop;

    public AnimalsView(Context context)
    {
        super(context);
        setKeepScreenOn(true);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        thread = new AnimalsThread(getHolder(), this);

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

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        /*bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.clouds_bottom));
        bgTop = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.clouds_top));

        bg.setVector(3);
        bgTop.setVector(7);

        bg.setPanelH(AnimalsView.HEIGHT);
        bgTop.setPanelH(AnimalsView.HEIGHT);

        bg.setPanelW(AnimalsView.WIDTH);
        bgTop.setPanelW(AnimalsView.WIDTH);*/

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    public void update()
    {
        bg.update();
        bgTop.update();
    }

    @Override
    public void draw(Canvas canvas)
    {
        final float scaleFactorX = getWidth()/WIDTH;
        final float scaleFactorY = getHeight()/HEIGHT;
        if(canvas!=null)
        {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            bgTop.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
