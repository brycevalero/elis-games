package com.brycevalero.www.elisgames.clouds;

/**
 * Created by bryce on 12/7/2015.
 */
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.brycevalero.www.elisgames.clouds.frog.Frog;
import com.brycevalero.www.elisgames.clouds.sound.SoundFX;
import com.brycevalero.www.elisgames.game.elements.Background;
import com.brycevalero.www.elisgames.R;


public class CloudsView extends SurfaceView implements SurfaceHolder.Callback
{
    public static Point screen;
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

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        screen = new Point();
        screen.set(metrics.widthPixels, metrics.heightPixels);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry)
        {
            try
            {
                thread.setRunning(false);
                thread.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            retry = false;
        }

        player.stop();
        player.release();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        bg = new Background(this.getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.clouds_bottom), this.screen);
        bgTop = new Background(this.getContext(), BitmapFactory.decodeResource(getResources(), R.drawable.clouds_top), this.screen);

        frogFloatingImg = BitmapFactory.decodeResource(getResources(), R.drawable.frogfloating);
        frogFallingImg = BitmapFactory.decodeResource(getResources(), R.drawable.frogfalling);

        //create our frogs and let them loose
        for(int i = 0; i < frogs.length; i++)
        {
            frogs[i] = new Frog(this.getContext(), this.screen);
            frogs[i].setFloatingImg(frogFloatingImg);
            frogs[i].setFallingImg(frogFallingImg);
            frogs[i].letLoose();
        }

        //how fast we paralax our two backgrounds
        bg.setVerticalVector(3);
        bgTop.setVerticalVector(7);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();

        player= MediaPlayer.create(this.getContext(), R.raw.slow_bells);
        player.setLooping(true);
        player.start();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            System.out.println("ORIENTATION_LANDSCAPE");

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            System.out.println("ORIENTATION_PORTRAIT");
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        for(int i = 0; i < frogs.length; i++)
        {
            if(frogs[i].isTouched(event))
            {
                frogs[i].setCurrentState(Frog.FALLING);
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
        boolean somethingsMoving = false;

        for(int i = 0; i < frogs.length; i++)
        {
            frogs[i].update();

            switch (frogs[i].getCurrentState())
            {
                //But if we are moving, we have to check the bounds
                case Frog.FLOATING:
                case Frog.FALLING:
                    somethingsMoving = true;
                    break;
            }
        }

        //if no frogs are moving (idled), let them loose
        if(!somethingsMoving)
        {
            System.out.println("LET THEM ALL LOOSE");
            for(int i = 0; i < frogs.length; i++)
            {
                frogs[i].letLoose();
            }
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleFactorX = getWidth()/this.screen.x;
        final float scaleFactorY = getHeight()/this.screen.y;
        if(canvas!=null)
        {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            bgTop.draw(canvas);

            for(int i = 0; i < frogs.length; i++)
            {
                frogs[i].draw(canvas);
            }
            canvas.restoreToCount(savedState);
        }
    }
}
