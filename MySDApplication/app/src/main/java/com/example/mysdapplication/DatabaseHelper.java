package com.example.mysdapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME;
    public static final String USER_TABLE="USER_INFORMATION";
    public static final String COL_1="ID";
    public static final String COL_2="USER_NAME";
    public static final String COL_3="USER_PASSWORD";
    public static final String COL_4="RECENT_SIGN_IN_USER";

    public static final String TITLE_COL="TITLE";
    public static final String NOTE_COL="NOTE";

    public DatabaseHelper(Context context,String D) {
        super(context,D,null, 1);
        DATABASE_NAME=D;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(DATABASE_NAME.equals("GoogleKeep.db"))
            db.execSQL("create table "+ USER_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,USER_NAME TEXT,USER_PASSWORD TEXT,RECENT_SIGN_IN_USER TEXT)");
        else
            db.execSQL("create table "+ USER_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,NOTE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(db);
    }
    public boolean insertData(String name,String password,String recentUser){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,recentUser);
        long result=db.insert(USER_TABLE,null,contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }
    public boolean insertData(String title,String note){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(TITLE_COL,title);
        contentValues.put(NOTE_COL,note);
        long result=db.insert(USER_TABLE,null,contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+USER_TABLE,null);
        return res;
    }
    public boolean isEmpty(String TableName){

        SQLiteDatabase database = this.getReadableDatabase();
        int NoOfRows = (int) DatabaseUtils.queryNumEntries(database,TableName);

        if (NoOfRows == 0){
            return true;
        }else {
            return false;
        }
    }
    public boolean updataData(String id,String title,String note){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(TITLE_COL,title);
        contentValues.put(NOTE_COL,note);
        db.update(USER_TABLE,contentValues, "ID = ?",new String[] { id });
        return true;
    }
    public int deleteData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(USER_TABLE, "ID = ?",new String[] {id});
    }
}
