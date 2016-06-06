package com.alvardev.android.abcplay.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserEntity extends RealmObject {

    @PrimaryKey
    private int idUser;
    private String name;
    private String nameSearch;
    private String user;
    private String password;
    private int type;
    private String dino;

    private int sColorsGroup;
    private int sColorsSingle;

    private int sShapesGroup;
    private int sShapesSingle;

    private int sVowelsGroup;
    private int sVowelsSingle;

    private String finalScore;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getsColorsGroup() {
        return sColorsGroup;
    }

    public void setsColorsGroup(int sColorsGroup) {
        this.sColorsGroup = sColorsGroup;
    }

    public int getsColorsSingle() {
        return sColorsSingle;
    }

    public void setsColorsSingle(int sColorsSingle) {
        this.sColorsSingle = sColorsSingle;
    }

    public int getsShapesGroup() {
        return sShapesGroup;
    }

    public void setsShapesGroup(int sShapesGroup) {
        this.sShapesGroup = sShapesGroup;
    }

    public int getsShapesSingle() {
        return sShapesSingle;
    }

    public void setsShapesSingle(int sShapesSingle) {
        this.sShapesSingle = sShapesSingle;
    }

    public int getsVowelsGroup() {
        return sVowelsGroup;
    }

    public void setsVowelsGroup(int sVowelsGroup) {
        this.sVowelsGroup = sVowelsGroup;
    }

    public int getsVowelsSingle() {
        return sVowelsSingle;
    }

    public void setsVowelsSingle(int sVowelsSingle) {
        this.sVowelsSingle = sVowelsSingle;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getDino() {
        return dino;
    }

    public void setDino(String dino) {
        this.dino = dino;
    }
}
