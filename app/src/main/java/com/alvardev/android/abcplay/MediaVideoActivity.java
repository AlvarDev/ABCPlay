package com.alvardev.android.abcplay;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MediaVideoActivity extends BaseAppCompatActivity {

    @InjectView(R.id.vvi_video) VideoView vviVideo;
    private int position = 0;
    private MediaController mediaControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_media_video);
        ButterKnife.inject(this);

        int srcVideo = getIntent().getIntExtra("srcVideo", R.raw.video_colors);
        setActions(srcVideo);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        //we use onSaveInstanceState in order to store the video playback position for orientation change
        savedInstanceState.putInt("Position", vviVideo.getCurrentPosition());
        vviVideo.pause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //we use onRestoreInstanceState in order to play the video playback from the stored position
        position = savedInstanceState.getInt("Position");
        vviVideo.seekTo(position);
    }

    private void setActions(int srcVideos){
        //set the media controller buttons
        if (mediaControls == null) {
            mediaControls = new MediaController(MediaVideoActivity.this);
        }

        try {
            //set the media controller in the VideoView
            vviVideo.setMediaController(mediaControls);

            //set the uri of the video to be played
            vviVideo.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + srcVideos));

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        vviVideo.requestFocus();
        //we also set an setOnPreparedListener in order to know when the video file is ready for playback
        vviVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                //if we have a position on savedInstanceState, the video playback should start from here
                vviVideo.seekTo(position);
                if (position == 0) {
                    vviVideo.start();
                } else {
                    //if we come from a resumed activity, video playback will be paused
                    vviVideo.pause();
                }
            }
        });
    }



}
