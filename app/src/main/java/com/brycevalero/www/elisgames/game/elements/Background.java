package com.brycevalero.www.elisgames.game.elements;

/**
 * Created by bryce on 12/7/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

public class Background extends BitmapObject{

    public Background(Context context, Bitmap res, Point screen)
    {
        super(context, Bitmap.createScaledBitmap(res, screen.x, screen.y, true));
        setVerticalVector(10);
        setObjectH(screen.y);
        setObjectW(screen.x);
    }

    @Override
    public void update()
    {
        super.update();
        if(this.getObjectY()>this.getObjectH())
        {
            this.setObjectY(0);
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        if(this.getObjectY()>0)
        {
            canvas.drawBitmap(this.getImage(), this.getObjectX(), this.getObjectY()-this.getObjectH(), null);
        }
    }
}
