package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_main);



        Button btn1 =(Button)findViewById(R.id.btnchangemenu);
        Button btn2 =(Button)findViewById(R.id.btnallocatechef);
        Button btn3 =(Button)findViewById(R.id.btnsetdiscount);
        Button btn4 =(Button)findViewById(R.id.btnviewcomment);

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ManagerActivity.this, ChangeMenuActivity.class);
                startActivity(intent);
                //finish();//Í£Ö¹µ±Ç°µÄActivity,Èç¹û²»Ð´,Ôò°´·µ»Ø¼ü»áÌø×ª»ØÔ­À´µÄActivity
            }

        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ManagerActivity.this, AllocateChefActivity.class);
                startActivity(intent);
                //finish();//Í£Ö¹µ±Ç°µÄActivity,Èç¹û²»Ð´,Ôò°´·µ»Ø¼ü»áÌø×ª»ØÔ­À´µÄActivity
            }

        });

        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ManagerActivity.this, SetDiscountActivity.class);
                startActivity(intent);
                //finish();//Í£Ö¹µ±Ç°µÄActivity,Èç¹û²»Ð´,Ôò°´·µ»Ø¼ü»áÌø×ª»ØÔ­À´µÄActivity
            }

        });

        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ManagerActivity.this, ViewCommentActivity.class);
                startActivity(intent);

            }

        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
