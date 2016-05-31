package com.alvardev.android.abcplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseAppCompatActivity {

    @InjectView(R.id.iv_login) ImageView ivLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        validateFirstTime();
        setActions();
    }

    private void validateFirstTime() {
        if (isFirstTime()) {
            setDefaultData();
            saveFirstTime();
        }
    }

    private void setActions(){
        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
