package com.alvardev.android.abcplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alvardev.android.abcplay.entities.UserEntity;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.Snackbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class LoginActivity extends BaseAppCompatActivity {

    @InjectView(R.id.iv_login) ImageView ivLogin;
    @InjectView(R.id.et_user) TextInputEditText etUser;
    @InjectView(R.id.et_password) TextInputEditText etPassword;
    @InjectView(R.id.content) View content;


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

    private void setActions() {
        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUser.getText().toString();
                String password = etPassword.getText().toString();
                if (validateLogin(user, password)) {
                    login(user, password);
                }
            }
        });
    }

    private boolean validateLogin(String user, String password) {
        etUser.setError(user.isEmpty() ? getString(R.string.s_no_user) : null);
        etPassword.setError(password.isEmpty() ? getString(R.string.s_no_password) : null);
        return !(user.isEmpty() || password.isEmpty());
    }

    private void login(String user, String password) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        RealmResults<UserEntity> users = realm.where(UserEntity.class)
                .equalTo("user", user)
                .equalTo("password", password)
                .findAll();
        if (users.size() == 1) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.putExtra("userType", users.get(0).getType());
            startActivity(intent);
            finish();
        } else {
            showSnack(getString(R.string.s_no_user_found));
        }
    }

    private void showSnack(String message) {
        Snackbar.make(content, message, Snackbar.LENGTH_LONG)
                .setAction("", null)
                .show();
    }

}
