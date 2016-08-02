package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "ORDER.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME1 = "order_table";
    private final static String ORDER_ID = "_id";
    private final static String TABLE = "table_name";
    private final static String DISH_NAME = "dish_name";
    private final static String DISH_PRICE = "dish_price";
    private final static String DISH_PIC = "dish_pic";
    private final static String DISH_STATUS = "dish_status";
    private final static String CHEF_NAME = "chef_name";
    private Context myContent;

    public OrderDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContent = context;
    }

    public void onCreate(SQLiteDatabase db) {

        String sql2 = "CREATE TABLE " + TABLE_NAME1 + " (" + ORDER_ID
                + " INTEGER primary key autoincrement, "+TABLE+" integer," + DISH_NAME + " text, "+ DISH_PRICE +" text, "+DISH_PIC+" integer, "+
                DISH_STATUS+" text, "+CHEF_NAME +" text);";
        db.execSQL(sql2);

        //Toast.makeText(myContent, "success created table", Toast.LENGTH_SHORT).show();
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> orders = new ArrayList<Order>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(TABLE);
                    int o_tableindex  = cur.getInt(numColumn);

                    int NUMPASS = cur.getColumnIndex(DISH_NAME);
                    String d_name = cur.getString(NUMPASS);
                    int COLPRICE = cur.getColumnIndex(DISH_PRICE);
                    String d_price = cur.getString(COLPRICE).toString();
                    int COLPIC = cur.getColumnIndex(DISH_PIC);
                    int d_pic = Integer.parseInt(cur.getString(COLPIC).toString());

                    int COLSTUS = cur.getColumnIndex(DISH_STATUS);
                    String d_staus = cur.getString(COLSTUS).toString();

                    int COLCHEF = cur.getColumnIndex(CHEF_NAME);
                    String c_name = cur.getString(COLCHEF);



                    Order order = new Order(o_tableindex,d_name,d_price,d_pic,d_staus,c_name);
                    orders.add(order);
                }while( cur.moveToNext());
            }
        }
        db.close();
        return orders;
    }

    public ArrayList<Order> getOrdersbyTableIndex(int tableIndex){
        ArrayList<Order> orders = new ArrayList<Order>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(TABLE);
                    int o_tableindex  = cur.getInt(numColumn);
                    if(o_tableindex==tableIndex){
                        int NUMPASS = cur.getColumnIndex(DISH_NAME);
                        String d_name = cur.getString(NUMPASS);

                        int COLPRICE = cur.getColumnIndex(DISH_PRICE);
                        String d_price = cur.getString(COLPRICE).toString();

                        int COLPIC = cur.getColumnIndex(DISH_PIC);
                        int d_pic = Integer.parseInt(cur.getString(COLPIC).toString());


                        int COLSTUS = cur.getColumnIndex(DISH_STATUS);
                        String d_staus = cur.getString(COLSTUS).toString();

                        int COLCHEF = cur.getColumnIndex(CHEF_NAME);
                        String c_name = cur.getString(COLCHEF);

                        Order order = new Order(o_tableindex,d_name,d_price,d_pic,d_staus,c_name);

                        orders.add(order);
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return orders;
    }

    public void FinishCooking(int tableindex){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String where = "table_name="+tableindex;
        String[] whereValue = { String.valueOf(tableindex) };
        //cv.put(ORDER_ID,id);
        cv.put(DISH_STATUS,"finish");
        db.update(TABLE_NAME1, cv, where, null);
    }

    public void UpdateStatus(int id, String status){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String where = ORDER_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };
        //cv.put(ORDER_ID,id);
        cv.put(DISH_STATUS,status);
        db.update(TABLE_NAME1, cv, where, whereValue);


    }

    public int getIDbyname(String dishname){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int NUMPASS = cur.getColumnIndex(DISH_NAME);
                    String d_name = cur.getString(NUMPASS);
                    if(d_name.equals(dishname)){
                        int DishSta = cur.getColumnIndex(DISH_STATUS);
                        String o_sta = cur.getString(DishSta);
                        if(o_sta.equals("Ordered")){
                            int COLID = cur.getColumnIndex(ORDER_ID);
                            int o_id = cur.getInt(COLID);
                            db.close();
                            return o_id;
                        }
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return 0;
    }

    public int getIDbynameAndTableNumber(String dishname,int tablenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int NUMPASS = cur.getColumnIndex(DISH_NAME);
                    String d_name = cur.getString(NUMPASS);
                    if(d_name.equals(dishname)){

                        int COLD = cur.getColumnIndex(TABLE);
                        int o_tb = cur.getInt(COLD);
                        if(tablenumber==o_tb){

                            int COLL = cur.getColumnIndex(DISH_STATUS);
                            String o_status = cur.getString(COLL);
                            if(!o_status.equals("Cooking")&&!o_status.equals("finish")){
                                int COLID = cur.getColumnIndex(ORDER_ID);
                                int o_id = cur.getInt(COLID);
                                db.close();
                                return o_id;
                            }
                        }
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return 0;
    }

    public long insertOrder(int tableindex, String dishName, String price,int pic_uri, String dish_status){

        SQLiteDatabase db = this.getWritableDatabase();
    /* ContentValues */
        ContentValues cv = new ContentValues();

        cv.put(TABLE,tableindex);
        cv.put(DISH_NAME,dishName);
        cv.put(DISH_PRICE, price);
        cv.put(DISH_PIC,pic_uri);
        cv.put(DISH_STATUS, dish_status);
        cv.put(CHEF_NAME, "Anyone");
        long row = db.insert(TABLE_NAME1,null,cv);
        return row;
    }

    public int deleteOrder(int tableindex){
        SQLiteDatabase db =getWritableDatabase();
        String[] args = {String.valueOf(tableindex)};
        return db.delete(TABLE_NAME1, "table_name="+tableindex, null);
    }

    public int deleteOneOrder(int orderindex){
        SQLiteDatabase db =getWritableDatabase();
        String[] args = {String.valueOf(orderindex)};
        return db.delete(TABLE_NAME1, "_id="+orderindex, null);
    }

    public void UpdateChefName(int tablenumber, String chefname){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        String where = TABLE + " = "+ tablenumber;
        //String[] whereValue = { Integer.toString(id) };
        //cv.put(ORDER_ID,id);
        cv.put(CHEF_NAME,chefname);
        db.update(TABLE_NAME1, cv, where, null);

    }

    public boolean everythingCooked(int tablenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int NUMPASS = cur.getColumnIndex(TABLE);
                    int tablenum = cur.getInt(NUMPASS);
                    if(tablenum==tablenumber){
                        int DishSta = cur.getColumnIndex(DISH_STATUS);
                        String o_sta = cur.getString(DishSta);
                        if(o_sta.equals("Ordered")){
                            db.close();
                            return false;
                        }
                    }
                }while( cur.moveToNext());
            }
        }
        db.close();
        return true;
    }
}

