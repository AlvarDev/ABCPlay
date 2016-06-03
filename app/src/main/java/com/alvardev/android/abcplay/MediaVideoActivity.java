package com.alvardev.android.abcplay;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MediaVideoActivity extends BaseAppCompatActivity {

    @InjectView(R.id.btn_tmp) Button btnTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_video);
        ButterKnife.inject(this);

        setActions();
    }

    private void setActions(){
        btnTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
    }

    private void play(){
        MediaPlayer mediaPlayer = MediaPlayer.create(MediaVideoActivity.this, R.raw.video_red);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

}
