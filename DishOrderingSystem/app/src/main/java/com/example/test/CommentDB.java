package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class CommentDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "COMMENT.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME1 = "comment_table";
    private final static String COMMENT_ID = "_id";
    private final static String CONTENT = "content";
    private final static String PERSON_NAME = "person_name";
    private final static String TIME = "comment_time";


    private Context myContent;

    public CommentDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        myContent = context;
    }

    public void onCreate(SQLiteDatabase db) {

        String sql2 = "CREATE TABLE " + TABLE_NAME1 + " (" + COMMENT_ID
                + " INTEGER primary key autoincrement, "+CONTENT+" text," + PERSON_NAME + " text, "+ TIME +" text);";
        db.execSQL(sql2);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Comment> getAllComments(){
        ArrayList<Comment> comments = new ArrayList<Comment>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+TABLE_NAME1, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    int numColumn = cur.getColumnIndex(TIME);
                    String c_time  = cur.getString(numColumn);
                    int NUMPASS = cur.getColumnIndex(PERSON_NAME);
                    String c_name = cur.getString(NUMPASS);
                    int COLPRICE = cur.getColumnIndex(CONTENT);
                    String c_comment = cur.getString(COLPRICE);
                    Comment com = new Comment(c_name,c_comment,c_time);
                    comments.add(com);
                }while( cur.moveToNext());
            }
        }
        db.close();
        return comments;
    }


    public long insertComment(String personname, String content, String time){

        SQLiteDatabase db = this.getWritableDatabase();
    /* ContentValues */
        ContentValues cv = new ContentValues();

        cv.put(CONTENT,content);
        cv.put(PERSON_NAME,personname);
        cv.put(TIME, time);

        long row = db.insert(TABLE_NAME1,null,cv);
        return row;
    }

}