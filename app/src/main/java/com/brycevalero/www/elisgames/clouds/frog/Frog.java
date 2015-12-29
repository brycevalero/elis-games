package com.brycevalero.www.elisgames.clouds.frog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.text.method.Touch;
import android.view.MotionEvent;

import com.brycevalero.www.elisgames.R;
import com.brycevalero.www.elisgames.game.elements.BitmapObject;
import com.brycevalero.www.elisgames.utilities.Utilities;

/**
 * Created by bryce on 12/24/2015.
 */
public class Frog extends BitmapObject {

    public static final int IDLE = 0;
    public static final int FLOATING = 1;
    public static final int FALLING = 2;

    public int currentState;
    public int floatingSpeed, fallingSpeed;
    public Bitmap floatingImg, fallingImg;

    public Frog(Context context, Bitmap res)
    {
        super(context, res);
        setVerticalVector(0);
        setHorizontalVector(0);
        setObjectH(0);
        setObjectW(200);
        setCurrentState(Frog.FLOATING);
        setObjectX(Utilities.rndInt(0, 720 - getObjectW()));
        setObjectY(1280);

        floatingSpeed = Utilities.rndInt(5, 15) * -1;
        fallingSpeed = 30;
    }

    public void update()
    {
        if(this.currentState == Frog.FLOATING)
            this.setObjectY(getObjectY() + floatingSpeed);

        if(this.currentState == Frog.FALLING)
            this.setObjectY(getObjectY() + fallingSpeed);
    }


    public void setCurrentState(int state)
    {
        currentState = state;
    }

    public int getCurrentState()
    {
        return currentState;
    }

    public void setFloatingSpeed(int speed){
        this.floatingSpeed = speed;
    }

    public void setFallingSpeed(int speed){
        this.fallingSpeed = speed;
    }

    public void setFloatingImg(Bitmap img)
    {
        this.floatingImg = img;
    }

    public void setFallingImg(Bitmap img)
    {
        this.fallingImg = img;
    }

    public boolean inBounds(float x, float y)
    {
        if(currentState == Frog.FLOATING) {
            if (y > this.getObjectY() && y < this.getObjectY() + 400){
                if (x > this.getObjectX() && x < this.getObjectX() + 200) {
                    return true;
                }
            }
        }
        return false;
    }

    public void resetFrog()
    {
        setCurrentState(Frog.FLOATING);
        setImage(floatingImg);
        setFloatingSpeed(Utilities.rndInt(5, 15) * -1);
        setObjectY(1280);
        setObjectX(Utilities.rndInt(0, 720 - getObjectW()));
    }

    public void idleFrog()
    {
        resetFrog();
        setCurrentState(Frog.IDLE);
    }
}
