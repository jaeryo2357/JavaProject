package com.example.jaery.javaproject;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;



public class WebToonItem {

    public WebToonItem(int Type,String title, String byname, String release,Bitmap Image)
    {

        this.Type=Type;
        this.Title=title;
        this.byname=byname;
        this.release=release;
        this.icon=Image;
    }
    public WebToonItem(int Type, Bitmap image)
    {
        this.Type=Type;

        this.icon=image;
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

    public void setType(int Type){this.Type=Type;}
    public int getType(){return Type;}




    private String Title;
    int Type;
    private String byname;
    private Bitmap icon;
    private String release;

   // ProgressBar progress;


}
