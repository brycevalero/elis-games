package com.brycevalero.www.elisgames.clouds.frog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
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
    public Point bounds;
    public int floatingSpeed, fallingSpeed;
    public Bitmap floatingImg, fallingImg;

    public Frog(Context context, Point bounds)
    {
        super(context);

        this.bounds = bounds;
        this.floatingSpeed = Utilities.rndInt(5, 15) * -1;
        this.fallingSpeed = 30;

        setVerticalVector(0);
        setHorizontalVector(0);
        resetLocation();
    }

    public void update()
    {
        if(this.currentState == Frog.FLOATING)
            this.setObjectY(getObjectY() + floatingSpeed);

        if(this.currentState == Frog.FALLING)
            this.setObjectY(getObjectY() + fallingSpeed);
    }

    public void updateState()
    {
        //if out of bounds change state
        switch (currentState) {
            case Frog.FLOATING:
                if (this.getObjectY() < -400) {
                    this.resetLocation();
                }
                break;
            case Frog.FALLING:
                if (this.getObjectY() > (this.bounds.y + this.getObjectH())) {
                    this.setCurrentState(Frog.IDLE);
                }
                break;
        }
    }

    public void letLoose()
    {
        this.setCurrentState(Frog.FLOATING);
    }

    public void setCurrentState(int state)
    {
        //Only set new state if it is different
        if(currentState != state)
        {
            currentState = state;

            //Then update the image accordingly
            switch (currentState) {
                case Frog.IDLE:
                case Frog.FLOATING:
                    setImage(floatingImg);
                    break;
                case Frog.FALLING:
                    setImage(fallingImg);
                    break;
            }

            System.out.println("New State: " + currentState);
        }
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

    public void resetLocation()
    {
        setObjectX(Utilities.rndInt(0, this.bounds.x - getObjectW()));
        setObjectY(this.bounds.y);
        setFloatingSpeed(Utilities.rndInt(5, 15) * -1);
    }

    public boolean isTouched(MotionEvent event)
    {
        if(currentState == Frog.FLOATING) {
            if (event.getY() > this.getObjectY() && event.getY() < this.getObjectY() + 400){
                if (event.getX() > this.getObjectX() && event.getX() < this.getObjectX() + 200) {
                    return true;
                }
            }
        }
        return false;
    }
}
