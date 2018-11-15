package com.example.jaery.javaproject;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jaery on 2018-05-04.
 */

public class DBOpenHelper {

    private static final String DATABASE_NAME = "Log.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Log (CHAP INTEGER PRIMARY KEY, IND INTEGER);");

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+Database.CreateDB._TABLENAME);
            onCreate(db);
        }


    }

    public DBOpenHelper(Context context){
        this.mCtx = context;
    }

    public String findID(){
        Cursor cursor= mDB.rawQuery("select * from Log",null);

        while(cursor.moveToNext()){
            return cursor.getString(2);
        }
        return null;
    }

    public String findPWD(){
        Cursor cursor= mDB.rawQuery("select * from Log",null);

        while(cursor.moveToNext()){
            return cursor.getString(3);
        }
        return null;
    }

    public int findauto()
    {
        Cursor cursor= mDB.rawQuery("select * from Log",null);

        while(cursor.moveToNext()){
                return cursor.getInt(1);
        }
        return -1;
    }

    public int getCount(){
        Cursor cursor= mDB.rawQuery("select * from Log",null);
        return cursor.getCount();
    }

    public void insert(int auto,String ID,String pwd)
    {
        if(getCount()==0)
        mDB.execSQL("INSERT INTO Log " + "VALUES ("+0+","+auto+",'"+ID+"','"+pwd+"');");
        else{
            mDB.execSQL("UPDATE Log set auto="+auto+", ID='"+ID+"', PWD='"+pwd+"';");
        }
    }

    public boolean UpdateAuto(int auto) //성공하면 true
    {

        mDB.execSQL("UPDATE Log set auto="+auto+";");

        if(findauto()==auto)
            return true;

        return false;
    }



    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

}
