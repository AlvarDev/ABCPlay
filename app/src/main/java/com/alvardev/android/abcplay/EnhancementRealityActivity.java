package com.alvardev.android.abcplay;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alvardev.android.abcplay.arutils.SimpleRenderer;

import org.artoolkit.ar.base.ARActivity;
import org.artoolkit.ar.base.rendering.ARRenderer;

public class EnhancementRealityActivity extends ARActivity {

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 133;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enhancement_reality);

        FrameLayout mainLayout = (FrameLayout)this.findViewById(R.id.mainLayout);

        if (!checkCameraPermission()) {
            //if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) { //ASK EVERY TIME - it's essential!
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }

        // When the screen is tapped, inform the renderer and vibrate the phone
        mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getSound(getPreference("letter"));
                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(40);
            }

        });

    }

    private void getSound(int letter){

        switch (letter){
            case 1:
                playSong(R.raw.sound_red);
                break;
            case 2:
                playSong(R.raw.sound_blue);
                break;
            case 3:
                playSong(R.raw.sound_yellow);
                break;
            case 4:
                playSong(R.raw.sound_green);
                break;
            case 5:
                playSong(R.raw.sound_pink);
                break;
            case 6:
                playSong(R.raw.sound_white);
                break;
        }
    }

    @Override
    protected ARRenderer supplyRenderer() {
        if (!checkCameraPermission()) {
            Toast.makeText(this, "No hay permisos para usar la camara - reiniciar la app", Toast.LENGTH_LONG).show();
            return null;
        }
        return new SimpleRenderer(EnhancementRealityActivity.this);
    }

    @Override
    protected FrameLayout supplyFrameLayout() {
        return (FrameLayout)this.findViewById(R.id.mainLayout);
    }


    private boolean checkCameraPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permisos concedidos", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public int getPreference(String key) {
        SharedPreferences preferences = this.getSharedPreferences("com.alvardev.android.abcplay", MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    private void playSong(int src){
        MediaPlayer mediaPlayer = MediaPlayer.create(EnhancementRealityActivity.this, src);
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }

}
