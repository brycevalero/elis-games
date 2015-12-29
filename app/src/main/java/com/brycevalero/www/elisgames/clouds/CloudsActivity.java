package com.brycevalero.www.elisgames.clouds;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.brycevalero.www.elisgames.game.elements.GameActivity;

/**
 * Created by bryce on 12/9/2015.
 */
public class CloudsActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new CloudsView(this));
    }
}
