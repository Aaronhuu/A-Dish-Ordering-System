package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DishDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "DISH.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME1 = "dish_table";
    private final static String DISH_ID = "_id";
    private final static String DISH_URI = "dish_pic";
    private final static String DISH_NAME = "dish_name";
    private final static String DISH_PRICE = "dish_price";


    private Context myContent;

    public DishDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContent = context;
    }

    public void onCreate(SQLiteDatabase db) {

        String sql2 = "CREATE TABLE " + TABLE_NAME1 + " (" + DISH_ID
                + " INTEGER primary key autoincrement, "+DISH_URI+" integer," + DISH_NAME + " text, "+ DISH_PRICE +" text);";
        db.execSQL(sql2);

        Toast.makeText(myContent, "success created table", Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Dish> getAllDishes(){
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(DISH_URI);
                    int d_uri  = cur.getInt(numColumn);
                    int NUMPASS = cur.getColumnIndex(DISH_NAME);
                    String d_name = cur.getString(NUMPASS);
                    int COLPRICE = cur.getColumnIndex(DISH_PRICE);
                    String d_price = cur.getString(COLPRICE).toString();
                    Dish dish = new Dish(d_uri,d_name,d_price);
                    dishes.add(dish);
                }while( cur.moveToNext());
            }
        }
        db.close();
        return dishes;
    }

    public Dish GetDishbyName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
        Dish dish=null;
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    int NUMPASS = cur.getColumnIndex(DISH_NAME);
                    String d_name = cur.getString(NUMPASS);
                    if(d_name.equals(name)){
                        int COLPRICE = cur.getColumnIndex(DISH_PRICE);
                        String d_price = cur.getString(COLPRICE).toString();
                        int numColumn = cur.getColumnIndex(DISH_URI);
                        int d_uri  = cur.getInt(numColumn);
                        dish = new Dish(d_uri,d_name,d_price);
                        return dish;
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();


        return dish;
    }

    public int GetDishIDbyName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        Dish dish=null;
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    int NUMPASS = cur.getColumnIndex(DISH_NAME);
                    String d_name = cur.getString(NUMPASS);
                    if(d_name.equals(name)){
                        int COLPRICE = cur.getColumnIndex(DISH_ID);
                        int d_id = cur.getInt(COLPRICE);
                        return d_id;
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();


        return 0;

    }


    public long insertDish(int uri, String dishName, String price){

        SQLiteDatabase db = this.getWritableDatabase();
/* ContentValues */
        ContentValues cv = new ContentValues();

        cv.put(DISH_URI,uri);
        cv.put(DISH_NAME,dishName);
        cv.put(DISH_PRICE,price);
        long row = db.insert("dish_table",null,cv);
        return row;
    }

    public void UpdateDish(Dish dish, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String where = DISH_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };
        cv.put(DISH_NAME,dish.getDishName());
        cv.put(DISH_PRICE,dish.getPrice());
        db.update(TABLE_NAME1, cv, where, whereValue);






    }


}

