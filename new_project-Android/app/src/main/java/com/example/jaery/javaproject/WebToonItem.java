package com.example.jaery.javaproject;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;



public class WebToonItem {

    public WebToonItem(int Type,String ID,String Genre,String title, String byname, String release,String Big,String small,Bitmap Image)
    {

        this.Type=Type;
        this.Title=title;
        this.byname=byname;
        this.release=release;
        this.icon=Image;
        this.Genre=Genre;
        this.smallimage=small;
        this.ID=ID;
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



    private  String Genre;
    private String ID;
    private String Title;

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBigimage() {
        return Bigimage;
    }

    public void setBigimage(String bigimage) {
        Bigimage = bigimage;
    }

    public String getSmallimage() {
        return smallimage;
    }

    public void setSmallimage(String smallimage) {
        this.smallimage = smallimage;
    }

    private String Bigimage;
    private String smallimage;
    int Type;
    private String byname;
    private Bitmap icon;
    private String release;

   // ProgressBar progress;


}
