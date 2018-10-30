package com.example.jaery.javaproject;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;



public class WebToonItem {

    public WebToonItem(String title, String byname, String release,Bitmap Image)
    {
        this.Title=title;
        this.byname=byname;
        this.release=release;
        this.icon=Image;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getByname() {
        return byname;
    }

    public void setByname(String byname) {
        this.byname = byname;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    String Title;
    String byname;
    Bitmap icon;
    String release;
   // ProgressBar progress;


}
