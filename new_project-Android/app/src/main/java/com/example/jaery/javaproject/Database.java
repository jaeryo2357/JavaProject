package com.example.jaery.javaproject;

import android.provider.BaseColumns;

public final class Database {

    public static final class CreateDB implements BaseColumns{
        public static final String _TABLENAME = "member";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +"DBIndex"+" integer primary key," +
                        "auto"+" integer, "
                        +"ID"+" text," +
                        "PWD"+" text);";
    }
}