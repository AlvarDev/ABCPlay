package com.alvardev.android.abcplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvardev.android.abcplay.adapters.MediaAdapter;
import com.alvardev.android.abcplay.entities.MediaEntity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MediaActivity extends BaseAppCompatActivity {

    @InjectView(R.id.iv_exit) ImageView ivExit;
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
        setActions();
        getMedia();
    }

    private void setActions(){
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getMedia(){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        media = realm.where(MediaEntity.class).equalTo("type", type).findAll();

        if (media.size() > 0) {
            setRecyclerView();
        }

    }

    private void setRecyclerView(){
        rvMedia.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =
                type == VIDEO ?
                        new LinearLayoutManager(this) :
                        new GridLayoutManager(this,2);
        rvMedia.setLayoutManager(mLayoutManager);

        MediaAdapter mAdapter = new MediaAdapter(media);
        rvMedia.setAdapter(mAdapter);
        rvMedia.setItemAnimator(new DefaultItemAnimator());
        rvMedia.setVisibility(View.VISIBLE);
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == VIDEO){
                    Intent intent = new Intent(MediaActivity.this, MediaVideoActivity.class);
                    startActivity(intent);
                }else{
                    showSnack(getString(R.string.s_tmp_audio));
                }
            }
        });

        tvNoResults.setVisibility(View.GONE);
    }

    private void showSnack(String message){
        Snackbar.make(content, message, Snackbar.LENGTH_LONG)
                .setAction("", null)
                .show();
    }

}
