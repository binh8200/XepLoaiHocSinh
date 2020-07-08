package com.example.test.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.test.Database.Data;
import com.example.test.Model.SinhVien;

import java.util.ArrayList;

public class SinhVienDAO {
    SQLiteDatabase db;

    public SinhVienDAO(Context context){
        Data dbHelper = new Data(context);
        db=dbHelper.getWritableDatabase();
    }

    public ArrayList<SinhVien> xemQLSV(){
        ArrayList<SinhVien> xemqlsv = new ArrayList<>();
        Cursor c=db.query("SINHVIEN",null,null,null,null,null,null);
        while (c.moveToNext())
        {
            SinhVien sv =new SinhVien();
            sv.setTen(c.getString(c.getColumnIndex("TEN")));
            sv.setDiem(c.getString(c.getColumnIndex("DIEM")));

            xemqlsv.add(sv);
        }
        return xemqlsv;
    }

    public long ThemSinhVien(SinhVien sv)
    {
        ContentValues values = new ContentValues();
        values.put("TEN",sv.getTen());
        values.put("DIEM",sv.getDiem());

        return db.insert("SINHVIEN", null,values);

    }

    public  int XoaSinhVien(SinhVien sv){
        return  db.delete("SINHVIEN","TEN=?",new String[]{sv.getTen()});
    }
}
