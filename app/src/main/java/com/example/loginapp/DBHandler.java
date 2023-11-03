package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable="create table users(user varchar(20) primary key,pass varchar(20))";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertValues(String username,String password){
        //SQLiteDatabase is a class with useful functions
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        //CONTENT VALUE IS A DATA STRUCTURE KEY VALUE PAIR
        contentValues.put("user",username);
        contentValues.put("pass",password);

        long result= db.insert("users",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean userExists(String username){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where user like ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }

    public boolean verify(String username,String password){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from users where user like ? and pass like ?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


}
