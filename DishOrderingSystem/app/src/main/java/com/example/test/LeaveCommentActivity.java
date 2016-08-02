package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class LeaveCommentActivity extends Activity {


    private CommentDB mCommentDB;
    private String ct;
    protected final static int MENU_CHECK = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_comment);
        mCommentDB=new CommentDB(this);
        final EditText comment = (EditText)findViewById(R.id.commentcontent);
        Button clear = (Button)findViewById(R.id.dropbutton);
        Button submit = (Button)findViewById(R.id.comfirmcomment);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaveCommentActivity.this, ""+comment.getText().toString(), Toast.LENGTH_SHORT).show();

                if(!comment.getText().toString().equals("")){
                    mCommentDB.insertComment(comment.getText().toString(),comment.getText().toString(),comment.getText().toString());
                }
                finish();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_CHECK, 0, "finish");
        //menu.add(Menu.NONE, MENU_QUIT, 0, "Quit");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_CHECK:
                finish();
                break;

        }
        return true;
    }


}