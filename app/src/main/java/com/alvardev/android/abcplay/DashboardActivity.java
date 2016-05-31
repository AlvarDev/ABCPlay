package com.alvardev.android.abcplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DashboardActivity extends BaseAppCompatActivity {

    @InjectView(R.id.iv_exit) ImageView ivExit;
    @InjectView(R.id.iv_playing) ImageView ivPlaying;
    @InjectView(R.id.iv_singing) ImageView ivSinging;
    @InjectView(R.id.iv_learning) ImageView ivLearning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.inject(this);

        setActions();
    }

    private void setActions(){
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(UnitsActivity.class, 0);
            }
        });

        ivSinging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(MediaActivity.class, VIDEO);
            }
        });

        ivLearning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(MediaActivity.class, AUDIO);
            }
        });
    }

    private void goTo(Class cls, int type){
        Intent intent = new Intent(DashboardActivity.this, cls);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
