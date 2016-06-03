package com.alvardev.android.abcplay;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alvardev.android.abcplay.entities.MediaEntity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class BaseAppCompatActivity extends AppCompatActivity {

    protected static final String NAME_PREFERENCE = "com.alvardev.android.abcplay.preferences";
    protected static final int VIDEO = 0;
    protected static final int AUDIO = 1;
    private static final String TAG = "BaseAppCompatAct";

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

        colors.setName("colors");
        colors.setIco(R.drawable.img_colors_video);
        colors.setPath("");
        colors.setType(VIDEO);
        colors.setOrder(1);
        colors.setMediaSrc(R.raw.video_red);

        shapes.setName("shapes");
        shapes.setIco(R.drawable.img_shapes_video);
        shapes.setPath("");
        shapes.setType(VIDEO);
        shapes.setOrder(2);
        shapes.setMediaSrc(R.raw.video_red);

        vowels.setName("vowels");
        vowels.setIco(R.drawable.img_vowels_video);
        vowels.setPath("");
        vowels.setType(VIDEO);
        vowels.setOrder(3);
        vowels.setMediaSrc(R.raw.video_red);

        family.setName("family");
        family.setIco(R.drawable.img_family_video);
        family.setPath("");
        family.setType(VIDEO);
        family.setOrder(4);
        family.setMediaSrc(R.raw.video_red);

        fruits.setName("fruits");
        fruits.setIco(R.drawable.img_fruits_video);
        fruits.setPath("");
        fruits.setType(VIDEO);
        fruits.setOrder(5);
        fruits.setMediaSrc(R.raw.video_red);

        animal.setName("animal");
        animal.setIco(R.drawable.img_animals_video);
        animal.setPath("");
        animal.setType(VIDEO);
        animal.setOrder(6);
        animal.setMediaSrc(R.raw.video_red);




        car.setName("car");
        car.setIco(R.drawable.img_car_audio);
        car.setPath("");
        car.setType(AUDIO);
        car.setOrder(1);
        car.setMediaSrc(R.raw.song_car);

        scissors.setName("scissors");
        scissors.setIco(R.drawable.img_scissors_audio);
        scissors.setPath("");
        scissors.setType(AUDIO);
        scissors.setOrder(2);
        scissors.setMediaSrc(R.raw.song_scissors);

        circle.setName("circle");
        circle.setIco(R.drawable.img_circle_audio);
        circle.setPath("");
        circle.setType(AUDIO);
        circle.setOrder(3);
        circle.setMediaSrc(R.raw.song_circle);

        start.setName("start");
        start.setIco(R.drawable.img_star_audio);
        start.setPath("");
        start.setType(AUDIO);
        start.setOrder(4);
        start.setMediaSrc(R.raw.song_start);

        girl.setName("girl");
        girl.setIco(R.drawable.img_girl_audio);
        girl.setPath("");
        girl.setType(AUDIO);
        girl.setOrder(5);
        girl.setMediaSrc(R.raw.song_girl);

        eye.setName("eye");
        eye.setIco(R.drawable.img_eye_audio);
        eye.setPath("");
        eye.setType(AUDIO);
        eye.setOrder(6);
        eye.setMediaSrc(R.raw.song_eye);

        ball.setName("ball");
        ball.setIco(R.drawable.img_ball_audio);
        ball.setPath("");
        ball.setType(AUDIO);
        ball.setOrder(7);
        ball.setMediaSrc(R.raw.song_ball);

        cube.setName("cube");
        cube.setIco(R.drawable.img_cube_audio);
        cube.setPath("");
        cube.setType(AUDIO);
        cube.setOrder(8);
        cube.setMediaSrc(R.raw.song_cube);

        pig.setName("pig");
        pig.setIco(R.drawable.img_pig_audio);
        pig.setPath("");
        pig.setType(AUDIO);
        pig.setOrder(9);
        pig.setMediaSrc(R.raw.song_pig);

        bicycle.setName("bicycle");
        bicycle.setIco(R.drawable.img_bicycle_audio);
        bicycle.setPath("");
        bicycle.setType(AUDIO);
        bicycle.setOrder(10);
        bicycle.setMediaSrc(R.raw.song_bicycle);

        boy.setName("boy");
        boy.setIco(R.drawable.img_boy_audio);
        boy.setPath("");
        boy.setType(AUDIO);
        boy.setOrder(11);
        boy.setMediaSrc(R.raw.song_boy);

        tree.setName("tree");
        tree.setIco(R.drawable.img_tree_audio);
        tree.setPath("");
        tree.setType(AUDIO);
        tree.setOrder(12);
        tree.setMediaSrc(R.raw.song_tree);

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
        Log.i(TAG, "Default Data done");
    }

    private void saveObject(RealmObject object) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        realm.beginTransaction();
        realm.copyToRealm(object);
        realm.commitTransaction();
    }

}
