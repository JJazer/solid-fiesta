package com.best.dex.endllessgame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class GodOfSound extends Service{
    private static final String LOGCAT = null;
    MediaPlayer objPlayer;


    public void onCreate(){
        super.onCreate();
        Log.d(LOGCAT, "Service Started!");
        objPlayer = MediaPlayer.create(this,R.raw.sound);
        objPlayer.setLooping(false);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        objPlayer.start();
        objPlayer.setVolume(1,1);
        Log.d(LOGCAT, "Media Player started!");
        /*if(objPlayer.isLooping() != true){
            Log.d(LOGCAT, "Problem in Playing Audio");
        }*/
        return 1;
    }

    public void onStop(){
        objPlayer.stop();
        objPlayer.release();
        objPlayer.setVolume(0,0);
    }

    public void onPause(){
        objPlayer.stop();
        objPlayer.release();
        objPlayer.setVolume(0,0);
    }
    public void onDestroy(){
        objPlayer.stop();
        objPlayer.release();
        objPlayer.setVolume(0,0);
    }
    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}