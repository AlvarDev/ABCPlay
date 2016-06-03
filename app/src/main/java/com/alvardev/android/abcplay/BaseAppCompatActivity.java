package com.alvardev.android.abcplay;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alvardev.android.abcplay.entities.MediaEntity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class BaseAppCompatActivity extends AppCompatActivity {

    protected static final String NAME_PREFERENCE = "com.alvardev.android.abcplay.preferences";
    protected static final int VIDEO = 0;
    protected static final int AUDIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean isFirstTime() {
        SharedPreferences preferences = this.getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE);
        return preferences.getBoolean("firstTime", true);
    }

    protected void saveFirstTime() {
        SharedPreferences preferences = this.getSharedPreferences(NAME_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstTime", false);
        editor.apply();
    }

    protected void setDefaultData(){
        MediaEntity colors = new MediaEntity();
        MediaEntity shapes = new MediaEntity();
        MediaEntity vowels = new MediaEntity();
        MediaEntity family = new MediaEntity();
        MediaEntity fruits = new MediaEntity();
        MediaEntity animal = new MediaEntity();

        MediaEntity car = new MediaEntity();
        MediaEntity scissors = new MediaEntity();
        MediaEntity circle = new MediaEntity();
        MediaEntity start = new MediaEntity();
        MediaEntity girl = new MediaEntity();
        MediaEntity eye = new MediaEntity();
        MediaEntity ball = new MediaEntity();
        MediaEntity cube = new MediaEntity();
        MediaEntity pig = new MediaEntity();
        MediaEntity bicycle = new MediaEntity();
        MediaEntity boy = new MediaEntity();
        MediaEntity tree = new MediaEntity();

        colors.setName("Colors");
        colors.setIco(R.drawable.tmp_color_video);
        colors.setPath("");
        colors.setType(VIDEO);

        shapes.setName("Shapes");
        shapes.setIco(R.drawable.tmp_color_video);
        shapes.setPath("");
        shapes.setType(VIDEO);

        vowels.setName("Vowels");
        vowels.setIco(R.drawable.tmp_color_video);
        vowels.setPath("");
        vowels.setType(VIDEO);

        family.setName("Family");
        family.setIco(R.drawable.tmp_color_video);
        family.setPath("");
        family.setType(VIDEO);

        fruits.setName("Fruits");
        fruits.setIco(R.drawable.tmp_color_video);
        fruits.setPath("");
        fruits.setType(VIDEO);

        animal.setName("Animal");
        animal.setIco(R.drawable.tmp_color_video);
        animal.setPath("");
        animal.setType(VIDEO);




        car.setName("Car");
        car.setIco(R.drawable.img_car_audio);
        car.setPath("");
        car.setType(AUDIO);

        scissors.setName("Scissors");
        scissors.setIco(R.drawable.img_car_audio);
        scissors.setPath("");
        scissors.setType(AUDIO);

        circle.setName("Circle");
        circle.setIco(R.drawable.img_circle_audio);
        circle.setPath("");
        circle.setType(AUDIO);

        start.setName("Start");
        start.setIco(R.drawable.img_star_audio);
        start.setPath("");
        start.setType(AUDIO);

        girl.setName("Girl");
        girl.setIco(R.drawable.img_girl_audio);
        girl.setPath("");
        girl.setType(AUDIO);

        eye.setName("Eye");
        eye.setIco(R.drawable.img_eye_audio);
        eye.setPath("");
        eye.setType(AUDIO);

        ball.setName("Ball");
        ball.setIco(R.drawable.img_ball_audio);
        ball.setPath("");
        ball.setType(AUDIO);

        cube.setName("Cube");
        cube.setIco(R.drawable.img_cube_audio);
        cube.setPath("");
        cube.setType(AUDIO);

        pig.setName("Pig");
        pig.setIco(R.drawable.img_car_audio);
        pig.setPath("");
        pig.setType(AUDIO);

        bicycle.setName("Bicycle");
        bicycle.setIco(R.drawable.img_bicycle_audio);
        bicycle.setPath("");
        bicycle.setType(AUDIO);

        boy.setName("Boy");
        boy.setIco(R.drawable.img_boy_audio);
        boy.setPath("");
        boy.setType(AUDIO);

        tree.setName("Tree");
        tree.setIco(R.drawable.img_tree_audio);
        tree.setPath("");
        tree.setType(AUDIO);

        saveObject(colors);
        saveObject(shapes);
        saveObject(vowels);
        saveObject(family);
        saveObject(fruits);
        saveObject(animal);

        saveObject(car);
        saveObject(scissors);
        saveObject(circle);
        saveObject(start);
        saveObject(girl);
        saveObject(eye);
        saveObject(ball);
        saveObject(cube);
        saveObject(pig);
        saveObject(bicycle);
        saveObject(boy);
        saveObject(tree);
    }

    private void saveObject(RealmObject object) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

}
