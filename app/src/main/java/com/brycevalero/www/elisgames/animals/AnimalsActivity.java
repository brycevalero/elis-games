package com.brycevalero.www.elisgames.animals;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.brycevalero.www.elisgames.clouds.CloudsView;
import com.brycevalero.www.elisgames.game.elements.GameActivity;

/**
 * Created by bryce on 12/24/2015.
 */
public class AnimalsActivity extends GameActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new AnimalsView(this));
    }
}
