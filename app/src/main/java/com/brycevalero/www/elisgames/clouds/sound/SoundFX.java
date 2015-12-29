package com.brycevalero.www.elisgames.clouds.sound;

import android.content.Context;

import android.media.MediaPlayer;
import com.brycevalero.www.elisgames.R;
import com.brycevalero.www.elisgames.utilities.Utilities;

/**
 * Created by bryce on 12/11/2015.
 */
public class SoundFX {

    private static MediaPlayer player;

    public static void playThemeSong(Context context)
    {
        player= MediaPlayer.create(context, R.raw.slow_bells);
        player.setLooping(true);
        player.start();
    }

    public static void playImpactSound(Context context)
    {
        player= MediaPlayer.create(context, SoundFX.getRandomImpact());
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
            ;
        });
    }

    public static void playScreamSound(Context context)
    {
        player= MediaPlayer.create(context, SoundFX.getRandomScream());
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.release();

            }
            ;
        });
    }

    public static int getRandomImpact()
    {
        int randomExplosion = Utilities.rndInt(0, 0);
        int resource = 0;

        if(randomExplosion == 0) {
            resource = R.raw.impact0;
        }

        return resource;
    }

    public static int getRandomScream()
    {
        int randomExplosion = Utilities.rndInt(0, 0);
        int resource = 0;

        if(randomExplosion == 0) {
            resource = R.raw.scream0;
        }

        return resource;
    }
}
