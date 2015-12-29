package com.brycevalero.www.elisgames;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import com.brycevalero.www.elisgames.fireworks.FireworksActivity;
import com.brycevalero.www.elisgames.clouds.CloudsActivity;
import com.brycevalero.www.elisgames.animals.AnimalsActivity;
import com.brycevalero.www.elisgames.fireworks.sound.SoundFX;

import java.io.IOException;

public class Games extends AppCompatActivity {

    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        player= MediaPlayer.create(getBaseContext(), R.raw.crazy_world_alt_version_ballad_loop_with_drums_guitar_electric_piano_and_acoustic_piano);
        player.setLooping(true);
    }

    public void onStop()
    {
        super.onStop();
        player.stop();
        try {
            player.prepare();
            player.seekTo(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("STOPPING");
    }

    public void onResume()
    {
        super.onResume();
        player.start();
        System.out.println("RESUMING");
    }

    protected void onDestroy(){
        super.onDestroy();
        player.stop();
        player.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_games, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openFireworks(View view){
        System.out.println("opening game one: fireworks");
        SoundFX.playExplosionSound(getApplicationContext());
        Intent intent = new Intent(this, FireworksActivity.class);
        startActivity(intent);
    }

    public void openGameTwo(View view){
        System.out.println("opening game two: clouds");
        Intent intent = new Intent(this, CloudsActivity.class);
        startActivity(intent);
    }

    public void openGameThree(View view){
        System.out.println("opening game three: animals");
        Intent intent = new Intent(this, AnimalsActivity.class);
        startActivity(intent);
    }

    public void openGameFour(View view){
        System.out.println("opening game four");
        Intent intent = new Intent(this, FireworksActivity.class);
        startActivity(intent);
    }
}
