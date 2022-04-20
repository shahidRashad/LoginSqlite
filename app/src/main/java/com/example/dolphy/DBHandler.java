package com.example.dolphy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "logindb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "login";
    private static final String COL_ID = "id";
    private static final String COL_USERNAME = "username";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME+" ("+COL_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_USERNAME+" TEXT, "+COL_EMAIL+" TEXT, "+
                COL_PASSWORD+" TEXT);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean isEmailExists(String e_mail) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{COL_ID,COL_USERNAME,COL_EMAIL,COL_PASSWORD},COL_EMAIL+"=?",new String[]{e_mail},null,null,null);

        if (cursor != null && cursor.moveToFirst()){
            return true;
        }
            return false;
    }

    public void addUser( String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME,username);
        values.put(COL_EMAIL,email);
        values.put(COL_PASSWORD,password);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }


    public LoginModel authenticate(LoginModel loginModel) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_EMAIL + "," + COL_PASSWORD + " FROM " + TABLE_NAME, null);
        if (cursor!= null && cursor.moveToFirst()) {
            LoginModel loginModel1 = new LoginModel(null, cursor.getString(0), cursor.getString(1));

            if (loginModel.getE_mail().equalsIgnoreCase(loginModel1.getE_mail()) && loginModel.getPass_word().equalsIgnoreCase(loginModel1.getPass_word())){
                return loginModel1;
            }
        }
        return null;
    }
}
