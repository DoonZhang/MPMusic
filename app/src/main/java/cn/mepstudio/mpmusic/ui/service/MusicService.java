package cn.mepstudio.mpmusic.ui.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by qtfreet on 2016/3/23.
 */
public class MusicService extends Service {
    MediaPlayer mp = null;

    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            mp = new MediaPlayer();
            mp.setDataSource("");
            mp.prepare();

        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        if (mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
