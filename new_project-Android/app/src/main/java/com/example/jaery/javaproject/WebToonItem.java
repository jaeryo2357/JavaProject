package com.example.jaery.javaproject;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;



public class WebToonItem {

    public WebToonItem(boolean change,int resource,String title, String byname, String release,Bitmap Image)
    {
        this.change=change;
        this.resource=resource;
        this.Title=title;
        this.byname=byname;
        this.release=release;
        this.icon=Image;
    }
    public WebToonItem(boolean change,int resource, Bitmap image)
    {
        this.change=change;
        this.resource=resource;
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

    public void setResource(int resource){this.resource=resource;}
    public int getResource(){return resource;}

    public boolean getChange() {
        return change;
    }

    private int resource;
    private String Title;
    private String byname;
    private Bitmap icon;
    private String release;
    private boolean change;
   // ProgressBar progress;


}
