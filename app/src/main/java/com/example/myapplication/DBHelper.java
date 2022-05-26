
package com.example.myapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    //Nama DB
    public static final String DATABASE_NAME = "biodata.db";
    //Nama Tabel
    public static final String TABLE_NAME = "table_mahasiswa";
    //Versi DB
    public static final int DATABASE_VERSION = 1;
    //Tabel Field
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAMA";
    public static final String COL_3 = "KELAS";
    public static final String COL_4 = "STUDI";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " TEXT PRIMARY KEY, " +
                COL_2 + " TEXT , " +
                COL_3 + " TEXT , " +
                COL_4 + " TEXT );"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Simpan Data
    public boolean insertData(String ID ,String nama,String Kelas ,String Studi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, Kelas);
        contentValues.put(COL_4, Studi);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    //Mengambil Data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM table_mahasiswa", null);
        return res;
    }
    //Merubah Data
    public boolean updateData(String ID ,String nama,String Kelas ,String Studi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, ID);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, Kelas);
        contentValues.put(COL_4, Studi);
        db.update(TABLE_NAME, contentValues, "ID=?", new String[]{ID});
        return true;
    }
    //Menghapus Data
    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
}
