package com.brycevalero.www.elisgames.fireworks;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.brycevalero.www.elisgames.game.elements.GameActivity;

public class FireworksActivity extends GameActivity {

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_MENU:
                //Toast.makeText(this, "Menu key pressed", Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_SEARCH:
                //Toast.makeText(this, "Search key pressed", Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "Hold Home to Exit", Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_HOME:
                Toast.makeText(this, "Hold Home to Exit", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new FireworksView(this));
    }
}