package com.example.test.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Data extends SQLiteOpenHelper {

    public final static String DBNAME="QUANLYSINHVIEN";
    public final static int DBVERSION=6;

    public Data(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String lenhSql= "CREATE TABLE  SINHVIEN( " +
                "TEN TEXT PRIMARY KEY NOT NULL UNIQUE, "+
                "DIEM TEXT NOT NULL)";
        db.execSQL(lenhSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql="DROP TABLE SINHVIEN";
        db.execSQL(sql);
    }
}
