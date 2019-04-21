package com.brycevalero.www.elisgames.game.elements;

/**
 * Created by bryce on 12/7/2015.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.ImageView;

public class BitmapObject extends ImageView{

    private Bitmap image;
    private int x, y, dx, dy, objectH, objectW;

    public BitmapObject(Context context)
    {
        super(context);
        dx = 0;
        dy = 0;
        objectH = 0;
        objectW = 0;
    }

    public BitmapObject(Context context, Bitmap res)
    {
        super(context);
        image = res;
        dx = 0;
        dy = 0;
        objectH = 0;
        objectW = 0;
    }

    public void update()
    {
        y+=dy;
        if(y>objectH){
            y=0;
        }
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawBitmap(image, x, y,null);
        if(y>0)
        {
            canvas.drawBitmap(image, x, y-objectH, null);
        }
    }

    public void setImage(Bitmap res)
    {
        this.image = res;
    }

    public void setVerticalVector(int dy)
    {
        this.dy = dy;
    }

    public void setHorizontalVector(int dx)
    {
        this.dx = dx;
    }

    public void setObjectH(int h) { this.objectH = h;}
    public int getObjectH(){ return this.objectH; }

    public void setObjectW(int w) { this.objectW = w;}
    public int getObjectW() { return this.objectW; }

    public void setObjectX(int x) { this.x = x; }
    public int getObjectX(){ return this.x; }

    public void setObjectY(int y) { this.y = y; }
    public int getObjectY(){ return this.y; }
}
