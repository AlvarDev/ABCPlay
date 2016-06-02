package com.alvardev.android.abcplay.arutils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.artoolkit.ar.base.ARToolKit;
import org.artoolkit.ar.base.rendering.ARRenderer;
import org.artoolkit.ar.base.rendering.Cube;

import javax.microedition.khronos.opengles.GL10;

public class SimpleRenderer extends ARRenderer {

    private int markerIDA = -1;
    private int markerIDB = -1;
    private int markerIDC = -1;
    private int markerIDD = -1;
    private int markerIDF = -1;
    private int markerIDG = -1;

    private Cube cube = new Cube(40.0f, 0.0f, 0.0f, 20.0f);
    private float angle = 0.0f;
    private boolean spinning = false;
    private int letter;

    private Context context;

    public SimpleRenderer(Context context){
        this.context = context;
    }

    @Override
    public boolean configureARScene() {
        markerIDA = ARToolKit.getInstance().addMarker("single;Data/multi/patt.a;80");
        markerIDB = ARToolKit.getInstance().addMarker("single;Data/multi/patt.b;80");
        markerIDC = ARToolKit.getInstance().addMarker("single;Data/multi/patt.c;80");
        markerIDD = ARToolKit.getInstance().addMarker("single;Data/multi/patt.d;80");
        markerIDF = ARToolKit.getInstance().addMarker("single;Data/multi/patt.f;80");
        markerIDG = ARToolKit.getInstance().addMarker("single;Data/multi/patt.g;80");

        return !(markerIDA < 0);
    }

    public void click() {
        spinning = !spinning;
        Log.i("TEST","spinning: "+spinning);

    }
    public int getLetter(){
        return letter;
    }

    public void draw(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadMatrixf(ARToolKit.getInstance().getProjectionMatrix(), 0);

        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glFrontFace(GL10.GL_CW);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        //A
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDA)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDA), 0);
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            if (spinning) angle += 5.0f;
            setLetter(1);
        }

        //B
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDB)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDB), 0);
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            if (spinning) angle += 5.0f;
            setLetter(2);
        }

        //C
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDC)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDC), 0);
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            if (spinning) angle += 5.0f;
            setLetter(3);
        }

        //D
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDD)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDD), 0);
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            if (spinning) angle += 5.0f;
            setLetter(4);
        }

        //F
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDF)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDF), 0);
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            if (spinning) angle += 5.0f;
            setLetter(5);
        }

        //G
        if (ARToolKit.getInstance().queryMarkerVisible(markerIDG)) {
            gl.glLoadMatrixf(ARToolKit.getInstance().queryMarkerTransformation(markerIDG), 0);
            gl.glPushMatrix();
            gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
            cube.draw(gl);
            gl.glPopMatrix();
            if (spinning) angle += 5.0f;
            setLetter(6);
        }

    }

    private void setLetter(int let){
        //Log.i("CHANGE","L: ["+letter+"] - let["+let+"]");
        if(letter != let){
            Log.i("Change","to: "+let);
            savePreference("letter",let);
        }
        //Log.i("CHANGE","L: ["+letter+"] - let["+let+"]");
    }

    public void savePreference(String key, int valor) {
        SharedPreferences preferences = context.getSharedPreferences("com.alvardev.android.abcplay", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, valor);
        editor.apply();
    }


}
