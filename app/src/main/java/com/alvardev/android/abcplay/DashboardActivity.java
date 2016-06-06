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
    @InjectView(R.id.iv_monitoring) ImageView ivMonitoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.inject(this);

        int userType = getIntent().getIntExtra("userType", STUDENT);
        setUI(userType);
        setActions();
    }

    private void setUI(int userType){
        ivPlaying.setVisibility(userType == TEACHER ? View.GONE : View.VISIBLE);
        ivSinging.setVisibility(userType == TEACHER ? View.GONE : View.VISIBLE);
        ivLearning.setVisibility(userType == TEACHER ? View.GONE : View.VISIBLE);
        ivMonitoring.setVisibility(userType == TEACHER ? View.VISIBLE : View.GONE);
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

        ivMonitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MonitoringActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goTo(Class cls, int type){
        Intent intent = new Intent(DashboardActivity.this, cls);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
