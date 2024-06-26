package com.alvardev.android.abcplay;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UnitsActivity extends BaseAppCompatActivity {

    @InjectView(R.id.content) View content;
    @InjectView(R.id.iv_exit) ImageView ivExit;
    @InjectView(R.id.iv_colors) ImageView ivColors;
    @InjectView(R.id.iv_shapes) ImageView ivShapes;
    @InjectView(R.id.iv_vowels) ImageView ivVowels;
    @InjectView(R.id.iv_family) ImageView ivFamily;
    @InjectView(R.id.iv_fruits) ImageView ivFruits;
    @InjectView(R.id.iv_animals) ImageView ivAnimals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_units);
        ButterKnife.inject(this);

        setActions();
        savePreference("letter",0);
    }

    private void setActions(){
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEnhancementReality("");
            }
        });

        ivShapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnack(getString(R.string.s_coming_soon));
            }
        });

        ivVowels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnack(getString(R.string.s_coming_soon));
            }
        });

        ivFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnack(getString(R.string.s_coming_soon));
            }
        });

        ivFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnack(getString(R.string.s_coming_soon));;
            }
        });

        ivAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnack(getString(R.string.s_coming_soon));
            }
        });
    }

    private void goToEnhancementReality(String mediaPath){
        Intent intent = new Intent(UnitsActivity.this, EnhancementRealityActivity.class);
        intent.putExtra("mediaPath", mediaPath);
        intent.putExtra("letter","a");
        startActivity(intent);
    }

    public void savePreference(String key, int valor) {
        SharedPreferences preferences = getSharedPreferences("com.alvardev.android.abcplay", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, valor);
        editor.apply();
    }

    private void showSnack(String message){
        Snackbar.make(content, message, Snackbar.LENGTH_SHORT)
                .setAction("", null)
                .show();
    }

}
