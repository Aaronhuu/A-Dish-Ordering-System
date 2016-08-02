package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;

public class PersonDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "PERSON.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "person_table";
    public final static String PERSON_ID = "person_id";
    public final static String PERSON_NAME = "person_name";
    public final static String PERSON_PASSWORD = "person_author";
    private final static String TABLE_NAME1 = "dish_table";
    private final static String VIP_STATUS = "vip_status";

    public PersonDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + PERSON_ID
                + " INTEGER primary key autoincrement, " + PERSON_NAME + " text, "+ PERSON_PASSWORD +" text, "+VIP_STATUS+" text);";

        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME1;
        db.execSQL(sql);
        db.execSQL(sql2);
        onCreate(db);
    }

    public boolean checkexist(String account){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(PERSON_NAME);
                    String P_name  = cur.getString(numColumn);
                    if(account.equals(P_name)){
                            db.close();
                            return true;
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return false;



    }

    public String getVipStatus(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(PERSON_NAME);
                    String P_name  = cur.getString(numColumn);
                    if(name.equals(P_name)){

                        int VSTA = cur.getColumnIndex(VIP_STATUS);
                        String v_status  = cur.getString(VSTA);

                        db.close();
                        return v_status;
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return "";
    }

    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }

    public boolean check(String name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(PERSON_NAME);
                    String P_name  = cur.getString(numColumn);
                    int NUMPASS = cur.getColumnIndex(PERSON_PASSWORD);
                    String p_pass = cur.getString(NUMPASS);
                    if(name.equals(P_name)){
                        if(password.equals(p_pass)){

                            db.close();
                            return true;
                        }else{
                            return false;
                        }
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return false;
    }

    public long insert(String name,String password,String vipstate) {
        SQLiteDatabase db = this.getWritableDatabase();
/* ContentValues */
        ContentValues cv = new ContentValues();
        cv.put(PERSON_NAME, name);
        cv.put(PERSON_PASSWORD, password);
        cv.put(VIP_STATUS,vipstate);
        long row = db.insert(TABLE_NAME, null, cv);
        return row;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = PERSON_ID + " = ?";
        String[] whereValue ={ Integer.toString(id) };
        db.delete(TABLE_NAME, where, whereValue);
    }

    public void update(int id, String name,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = PERSON_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };

        ContentValues cv = new ContentValues();
        cv.put(PERSON_NAME, name);
        cv.put(PERSON_PASSWORD, password);
        db.update(TABLE_NAME, cv, where, whereValue);
    }

    public ArrayList<Person> getChef(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Person> persons= new ArrayList<Person>();
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(PERSON_NAME);
                    String P_name  = cur.getString(numColumn);
                    int NUMPASS = cur.getColumnIndex(PERSON_PASSWORD);
                    String p_pass = cur.getString(NUMPASS);
                    if(P_name.contains("chef")){
                        Person p = new Person(P_name,p_pass);
                        persons.add(p);
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return persons;



    }

    public void clearTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,1,1);
    }

}

