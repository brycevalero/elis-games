package com.brycevalero.www.elisgames.game.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
* Bitmap Object
*
* <P>Representation of image on screen.
*
* @author Bryce Valero
* @version 1.0
*/
public class BitmapObject extends ImageView{

    private Bitmap image;
    private int x, y, dx, dy, objectH, objectW;

    /**
    * Constructor.
    *
    * @param context (required)
    */
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
        objectH = res.getHeight();
        objectW = res.getWidth();
    }

    public void update()
    {
        y+=dy;
    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        canvas.drawBitmap(image, x, y,null);
    }

    public Bitmap getImage()
    {
        return this.image;
    }

    public void setImage(Bitmap res)
    {
        this.image = res;
        objectH = res.getHeight();
        objectW = res.getWidth();
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
