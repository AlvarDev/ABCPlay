package com.alvardev.android.abcplay;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
    private FrameLayout mainLayout;
    private String letter;

    private SimpleRenderer simpleRenderer = new SimpleRenderer(EnhancementRealityActivity.this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enhancement_reality);

        mainLayout = (FrameLayout)this.findViewById(R.id.mainLayout);
        letter = getIntent().getStringExtra("letter");

        if (!checkCameraPermission()) {
            //if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) { //ASK EVERY TIME - it's essential!
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        }

        // When the screen is tapped, inform the renderer and vibrate the phone
        mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.i("Render", "Letter: "+getPreference("letter"));
                //simpleRenderer.click();
                Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vib.vibrate(40);
            }

        });

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
        SharedPreferences preferencias = this.getSharedPreferences("com.alvardev.android.abcplay", MODE_PRIVATE);
        return preferencias.getInt(key, 0);
    }

}
