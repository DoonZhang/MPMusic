package cn.mepstudio.mpmusic.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.widget.PlayerView;
import com.iflytek.sunflower.FlowerCollector;
import cn.mepstudio.mpmusic.R;
import cn.mepstudio.mpmusic.model.Constant.Constants;

public class VideoActivity extends AppCompatActivity {
    private PlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater();
        View rootView = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, null);
        setContentView(rootView);
        String videoName = getIntent().getExtras().getString(Constants.NAME);
        String url = getIntent().getExtras().getString(Constants.URL);
        if (TextUtils.isEmpty(videoName) || TextUtils.isEmpty(url)) {
            Toast.makeText(this, "播放链接无效~", Toast.LENGTH_SHORT).show();
            return;
        }
        player = new PlayerView(this, rootView)

                .setTitle(videoName)
                .setOnlyFullScreen(true)
                .forbidTouch(false)
                .hideMenu(true)
                .setPlaySource("默认", url).setShowSpeed(true)
                .setPlayerBackListener(new OnPlayerBackListener() {
                    @Override
                    public void onPlayerBack() {
                        //这里可以简单播放器点击返回键
                        finish();
                    }
                })
                .startPlay();

    }

    @Override
    protected void onPause() {
        super.onPause();
        FlowerCollector.onPause(this);
        if (player != null) {
            player.onPause();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        FlowerCollector.onResume(this);
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
