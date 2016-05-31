package com.alvardev.android.abcplay.entities;

import io.realm.RealmObject;

public class MediaEntity extends RealmObject {

    private String path;
    private int type;
    private String name;
    private int ico;

    public MediaEntity() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIco() {
        return ico;
    }

    public void setIco(int ico) {
        this.ico = ico;
    }
}
