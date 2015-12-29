package com.brycevalero.www.elisgames.game.elements;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.brycevalero.www.elisgames.R;

/**
 * Created by bryce on 12/25/2015.
 */
public class Block extends ImageView implements View.OnTouchListener {

    public Block(Context context)
    {
        super(context);
        this.init();
    }

    public void init()
    {
        this.setImageResource(R.drawable.frogfloating);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
