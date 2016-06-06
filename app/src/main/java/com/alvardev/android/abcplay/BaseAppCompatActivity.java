package com.alvardev.android.abcplay;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alvardev.android.abcplay.entities.MediaEntity;
import com.alvardev.android.abcplay.entities.UserEntity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class BaseAppCompatActivity extends AppCompatActivity {

    protected static final String NAME_PREFERENCE = "com.alvardev.android.abcplay.preferences";
    protected static final int VIDEO = 0;
    protected static final int AUDIO = 1;
    protected static final int NO_MEDIA_SRC = 3;
    protected static final int GENERAL = 0;
    protected static final int STUDENT = 1;
    protected static final int TEACHER = 2;
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
        colors.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_colors_video.png");
        colors.setType(VIDEO);
        colors.setOrder(1);
        colors.setMediaSrc(R.raw.video_colors);

        shapes.setName("shapes");
        shapes.setIco(R.drawable.img_shapes_video);
        shapes.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_shapes_video.png");
        shapes.setType(VIDEO);
        shapes.setOrder(2);
        shapes.setMediaSrc(R.raw.video_shapes);

        vowels.setName("vowels");
        vowels.setIco(R.drawable.img_vowels_video);
        vowels.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_vowels_video.png");
        vowels.setType(VIDEO);
        vowels.setOrder(3);
        vowels.setMediaSrc(R.raw.video_vowels);

        family.setName("family");
        family.setIco(R.drawable.img_family_video);
        family.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_family_video.png");
        family.setType(VIDEO);
        family.setOrder(4);
        family.setMediaSrc(NO_MEDIA_SRC);

        fruits.setName("fruits");
        fruits.setIco(R.drawable.img_fruits_video);
        fruits.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_fruits_video.png");
        fruits.setType(VIDEO);
        fruits.setOrder(5);
        fruits.setMediaSrc(NO_MEDIA_SRC);

        animal.setName("animal");
        animal.setIco(R.drawable.img_animals_video);
        animal.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_animals_video.png");
        animal.setType(VIDEO);
        animal.setOrder(6);
        animal.setMediaSrc(NO_MEDIA_SRC);




        car.setName("car");
        car.setIco(R.drawable.img_car_audio);
        car.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_car_audio.png");
        car.setType(AUDIO);
        car.setOrder(1);
        car.setMediaSrc(R.raw.song_car);

        scissors.setName("scissors");
        scissors.setIco(R.drawable.img_scissors_audio);
        scissors.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_scissors_audio.png");
        scissors.setType(AUDIO);
        scissors.setOrder(2);
        scissors.setMediaSrc(R.raw.song_scissors);

        circle.setName("circle");
        circle.setIco(R.drawable.img_circle_audio);
        circle.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_circle_audio.png");
        circle.setType(AUDIO);
        circle.setOrder(3);
        circle.setMediaSrc(R.raw.song_circle);

        start.setName("start");
        start.setIco(R.drawable.img_star_audio);
        start.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_star_audio.png");
        start.setType(AUDIO);
        start.setOrder(4);
        start.setMediaSrc(R.raw.song_start);

        girl.setName("girl");
        girl.setIco(R.drawable.img_girl_audio);
        girl.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_girl_audio.png");
        girl.setType(AUDIO);
        girl.setOrder(5);
        girl.setMediaSrc(R.raw.song_girl);

        eye.setName("eye");
        eye.setIco(R.drawable.img_eye_audio);
        eye.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_eye_audio.png");
        eye.setType(AUDIO);
        eye.setOrder(6);
        eye.setMediaSrc(R.raw.song_eye);

        ball.setName("ball");
        ball.setIco(R.drawable.img_ball_audio);
        ball.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_ball_audio.png");
        ball.setType(AUDIO);
        ball.setOrder(7);
        ball.setMediaSrc(R.raw.song_ball);

        cube.setName("cube");
        cube.setIco(R.drawable.img_cube_audio);
        cube.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_cube_audio.png");
        cube.setType(AUDIO);
        cube.setOrder(8);
        cube.setMediaSrc(R.raw.song_cube);

        pig.setName("pig");
        pig.setIco(R.drawable.img_pig_audio);
        pig.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_pig_audio.png");
        pig.setType(AUDIO);
        pig.setOrder(9);
        pig.setMediaSrc(R.raw.song_pig);

        bicycle.setName("bicycle");
        bicycle.setIco(R.drawable.img_bicycle_audio);
        bicycle.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_bicycle_audio.png");
        bicycle.setType(AUDIO);
        bicycle.setOrder(10);
        bicycle.setMediaSrc(R.raw.song_bicycle);

        boy.setName("boy");
        boy.setIco(R.drawable.img_boy_audio);
        boy.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_boy_audio.png");
        boy.setType(AUDIO);
        boy.setOrder(11);
        boy.setMediaSrc(R.raw.song_boy);

        tree.setName("tree");
        tree.setIco(R.drawable.img_tree_audio);
        tree.setPath("https://raw.githubusercontent.com/AlvarDev/ABCPlay/master/app/src/main/res/drawable/img_tree_audio.png");
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



        UserEntity teacherdino = new UserEntity();
        UserEntity teacherdino1 = new UserEntity();

        UserEntity student1 = new UserEntity();
        UserEntity student2 = new UserEntity();
        UserEntity student3 = new UserEntity();
        UserEntity student4 = new UserEntity();
        UserEntity student5 = new UserEntity();
        UserEntity student6 = new UserEntity();
        UserEntity student7 = new UserEntity();
        UserEntity student8 = new UserEntity();
        UserEntity student9 = new UserEntity();
        UserEntity student0 = new UserEntity();

        teacherdino.setIdUser(10);
        teacherdino.setName("Teacher Dino");
        teacherdino.setUser("TEACHERDINO");
        teacherdino.setPassword("usmp");
        teacherdino.setType(TEACHER);

        teacherdino1.setIdUser(11);
        teacherdino1.setName("Teacher Dino 1");
        teacherdino1.setUser("TEACHERDINO1");
        teacherdino1.setPassword("usmp");
        teacherdino1.setType(TEACHER);

        student0.setIdUser(0);
        student0.setName("Jenner Andre Orozco Angeles");
        student0.setNameSearch(student0.getName().toLowerCase());
        student0.setUser("DINO");
        student0.setPassword("usmp");
        student0.setType(STUDENT);
        //student0.setMale(true);

        student1.setIdUser(1);
        student1.setName("Mia Jazmin Catos Perez");
        student1.setNameSearch(student1.getName().toLowerCase());
        student1.setUser("--");
        student1.setPassword("--");
        student1.setType(STUDENT);
        //student1.setMale(false);

        student2.setIdUser(2);
        student2.setName("Angel Fabrizio Salinas Casquino");
        student2.setNameSearch(student2.getName().toLowerCase());
        student2.setUser("--");
        student2.setPassword("--");
        student2.setType(STUDENT);
        //student2.setMale(true);

        student3.setIdUser(3);
        student3.setName("Aaron Stefano Soto Bueno");
        student3.setNameSearch(student3.getName().toLowerCase());
        student3.setUser("--");
        student3.setPassword("--");
        student3.setType(STUDENT);
        //student3.setMale(true);

        student4.setIdUser(4);
        student4.setName("Briana Sanchez Paz");
        student4.setNameSearch(student4.getName().toLowerCase());
        student4.setUser("--");
        student4.setPassword("--");
        student4.setType(STUDENT);
        //student4.setMale(false);

        student5.setIdUser(5);
        student5.setName("Makeyla Solis Castro");
        student5.setNameSearch(student5.getName().toLowerCase());
        student5.setUser("--");
        student5.setPassword("--");
        student5.setType(STUDENT);
        //student5.setMale(false);

        student6.setIdUser(6);
        student6.setName("Juan David Romero Chacaltana");
        student6.setNameSearch(student6.getName().toLowerCase());
        student6.setUser("--");
        student6.setPassword("--");
        student6.setType(STUDENT);
        //student6.setMale(true);

        student7.setIdUser(7);
        student7.setName("Georgia Romero Chacaltana");
        student7.setNameSearch(student7.getName().toLowerCase());
        student7.setUser("--");
        student7.setPassword("--");
        student7.setType(STUDENT);
        //student7.setMale(false);

        student8.setIdUser(8);
        student8.setName("Kristel Huachin Lopez");
        student8.setNameSearch(student8.getName().toLowerCase());
        student8.setUser("--");
        student8.setPassword("--");
        student8.setType(STUDENT);
        //student8.setMale(false);

        student9.setIdUser(9);
        student9.setName("Vannia Advincula Banda");
        student9.setNameSearch(student9.getName().toLowerCase());
        student9.setUser("--");
        student9.setPassword("--");
        student9.setType(STUDENT);
        //student9.setMale(false);

        saveObject(teacherdino);
        saveObject(teacherdino1);

        saveObject(student0);
        saveObject(student1);
        saveObject(student2);
        saveObject(student3);
        saveObject(student4);
        saveObject(student5);
        saveObject(student6);
        saveObject(student7);
        saveObject(student8);
        saveObject(student9);

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
