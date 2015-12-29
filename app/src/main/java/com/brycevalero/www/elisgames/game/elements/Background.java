package com.brycevalero.www.elisgames.game.elements;

/**
 * Created by bryce on 12/7/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background extends BitmapObject{

    public Background(Context context, Bitmap res)
    {
        super(context, res);
        setVerticalVector(10);
        setObjectH(1280);
        setObjectW(720);
    }
}