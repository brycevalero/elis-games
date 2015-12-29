package com.brycevalero.www.elisgames.fireworks.sound;

import android.content.Context;

import android.media.MediaPlayer;
import com.brycevalero.www.elisgames.R;
import com.brycevalero.www.elisgames.utilities.Utilities;

/**
 * Created by bryce on 12/11/2015.
 */
public class SoundFX {

    private static MediaPlayer player;

    public static void playExplosionSound(Context context)
    {
        player= MediaPlayer.create(context, SoundFX.getRandomExplosion());
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
            ;
        });
    }

    public static int getRandomExplosion()
    {
        int randomExplosion = Utilities.rndInt(0, 8);
        int resource = 0;

        if(randomExplosion == 0) {
            resource = R.raw.firework0;
        }
        else if(randomExplosion == 1) {
            resource = R.raw.firework1;
        }
        else if(randomExplosion == 2) {
            resource = R.raw.firework2;
        }
        else if(randomExplosion == 3) {
            resource = R.raw.firework0;
        }
        else if(randomExplosion == 4) {
            resource = R.raw.firework4;
        }
        else if(randomExplosion == 5) {
            resource = R.raw.firework5;
        }
        else if(randomExplosion == 6) {
            resource = R.raw.firework6;
        }
        else if(randomExplosion == 7) {
            resource = R.raw.firework7;
        }
        else if(randomExplosion == 8) {
            resource = R.raw.firework8;
        }

        return resource;
    }
}
