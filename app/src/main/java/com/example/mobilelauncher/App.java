package com.example.mobilelauncher;

import android.graphics.drawable.Drawable;

public class App {

    private String name;
    private String packageName;
    private Drawable image;

    public App(String name, String packageName, Drawable image){
        this.name=name;
        this.packageName=packageName;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    public Drawable getImage() {
        return image;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
