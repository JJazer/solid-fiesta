package com.best.dex.endllessgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;


public class EndlessRunningGameActivity extends Activity {
    /** Called when the activity is first created. */
    private GameView gameView;
    public Intent PlaySound;
    private int counter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlaySound = new Intent(this, GodOfSound.class);

        startService(PlaySound);

        gameView = new GameView(this);

        setContentView(gameView); /*Sets the displaycontent to our new object GameView*/


    }

    public boolean onTouchEvent(MotionEvent e)
    {
        if (gameView.counterer)
        {
            stopService(PlaySound);
            System.exit(0);
        }
        return false;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        gameView.gameLoopThread.running = false;
        stopService(PlaySound);

        finish();
    }


    public void onDestroy()
    {
        super.onDestroy();
        gameView.gameLoopThread.running = false;
        stopService(PlaySound);
        System.exit(0);
    }

    public void onResume()
    {
        super.onResume();
        //startService(PlaySound);



        //finish();
    }










}
