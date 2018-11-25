package com.veeresh.b37_databaserecyclereg1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by skillgun on 02/09/2017.
 */

//8. create MyDatabase file, write code for data base file.
public class MyDatabase {
    private MyHelper mh;
    private SQLiteDatabase sdb;

    public MyDatabase(Context c){
        mh = new MyHelper(c, "techpalle.db", null, 1);
    }
    public void open(){
        sdb = mh.getWritableDatabase();
    }
    //dml operations
    public void insertStudent(String name, String sub){
        ContentValues cv = new ContentValues();
        cv.put("sname", name);
        cv.put("sub", sub);
        sdb.insert("student", null, cv);
    }
    public Cursor queryStudent(){
        Cursor c = sdb.query("student", null, null, null, null, null, null);
        return c;
    }
    public void close(){
        sdb.close();
    }
    public class MyHelper extends SQLiteOpenHelper{
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table student(_id integer primary key, sname text, sub text);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }
    }
}
