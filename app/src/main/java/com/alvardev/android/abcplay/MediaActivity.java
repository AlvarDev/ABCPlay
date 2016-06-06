package com.alvardev.android.abcplay;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.android.abcplay.adapters.MediaAdapter;
import com.alvardev.android.abcplay.entities.MediaEntity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MediaActivity extends BaseAppCompatActivity {

    private static final String TAG = "MediaActivity";
    @InjectView(R.id.iv_exit) ImageView ivExit;
    @InjectView(R.id.iv_logo) ImageView ivLogo;
    @InjectView(R.id.et_search) EditText etSearch;
    @InjectView(R.id.iv_search) ImageView ivSearch;
    @InjectView(R.id.tv_no_results) TextView tvNoResults;
    @InjectView(R.id.rv_media) RecyclerView rvMedia;
    @InjectView(R.id.content) View content;
    //@InjectView(R.id.progress_bar) View progressBar;

    private RealmResults<MediaEntity> media;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        ButterKnife.inject(this);

        type = getIntent().getIntExtra("type", VIDEO);
        setLogo();
        setActions();
        getMedia();
    }

    private void setLogo(){
        ivLogo.setImageResource(type == VIDEO ? R.drawable.img_logo_singing : R.drawable.img_logo_learning);
    }

    private void setActions(){
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearch.getText().toString();
                search(query);
            }
        });
    }

    private void getMedia(){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        media = realm.where(MediaEntity.class).equalTo("type", type).findAll();
        media.sort("order", Sort.ASCENDING);

        Log.i(TAG,"type: "+type);
        for(MediaEntity med :  media){
            Log.i(TAG, "["+med.getName()+"]");
        }

        if (media.size() > 0) {
            setRecyclerView();
        }else{
            tvNoResults.setVisibility(View.VISIBLE);
        }

    }

    private void search(String query){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);

        if(query.isEmpty()){
            media = realm.where(MediaEntity.class)
                    .equalTo("type", type)
                    .findAll();
        }else {
            media = realm.where(MediaEntity.class)
                    .equalTo("type", type)
                    .contains("name", query)
                    .findAll();
        }

        media.sort("order", Sort.ASCENDING);

        if (media.size() > 0) {
            setRecyclerView();
        }else{
            tvNoResults.setVisibility(View.VISIBLE);
        }

    }

    private void setRecyclerView(){
        rvMedia.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                type == VIDEO ?
                        new LinearLayoutManager(this) :
                        new GridLayoutManager(this,2);
        rvMedia.setLayoutManager(mLayoutManager);

        MediaAdapter mAdapter = new MediaAdapter(media, MediaActivity.this);
        rvMedia.setAdapter(mAdapter);
        rvMedia.setItemAnimator(new DefaultItemAnimator());
        rvMedia.setVisibility(View.VISIBLE);
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = rvMedia.getChildLayoutPosition(v);
                if(type == VIDEO){
                    if(media.get(pos).getMediaSrc()==NO_MEDIA_SRC){
                        showSnack(getString(R.string.s_coming_soon));
                    }else{
                        Intent intent = new Intent(MediaActivity.this, MediaVideoActivity.class);
                        intent.putExtra("srcVideo", media.get(pos).getMediaSrc());
                        startActivity(intent);
                    }

                }else{
                    playSong(media.get(pos).getMediaSrc());
                }
            }
        });

        tvNoResults.setVisibility(View.GONE);
    }

    private void playSong(int src){
        MediaPlayer mediaPlayer = MediaPlayer.create(MediaActivity.this, src);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

    private void showSnack(String message){
        Snackbar.make(content, message, Snackbar.LENGTH_SHORT)
                .setAction("", null)
                .show();
    }

}
