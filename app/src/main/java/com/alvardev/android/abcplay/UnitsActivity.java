package com.alvardev.android.abcplay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class UnitsActivity extends BaseAppCompatActivity {

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
                goToEnhancementReality("");
            }
        });

        ivVowels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEnhancementReality("");
            }
        });

        ivFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEnhancementReality("");
            }
        });

        ivFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEnhancementReality("");
            }
        });

        ivAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToEnhancementReality("");
            }
        });
    }

    private void goToEnhancementReality(String mediaPath){
        Intent intent = new Intent(UnitsActivity.this, EnhancementRealityActivity.class);
        intent.putExtra("mediaPath", mediaPath);
        intent.putExtra("letter","a");
        startActivity(intent);
    }

}
