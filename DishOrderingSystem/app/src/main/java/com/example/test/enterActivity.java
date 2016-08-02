package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;
import android.content.Intent;







public class enterActivity extends Activity {

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    int state = 0;//layout 1;
    private PersonDB mPersonDB;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //inheritance the method of Activity's onTouchEvent
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //when finger press on
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //when fingers leave
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
                //Toast.makeText(MainActivity.this, "slide up", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(enterActivity.this, menuActivity.class);
                intent.putExtra("VIPstate","customer");
                startActivity(intent);
                //finish();



            } else if(y2 - y1 > 50) {
                //Toast.makeText(MainActivity.this, "slide down", Toast.LENGTH_SHORT).show();
            } else if(x1 - x2 > 50) {
                //Toast.makeText(MainActivity.this, "slide left", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(enterActivity.this, Layout2.class);
                startActivity(intent);

            } else if(x2 - x1 > 50) {
                //Toast.makeText(MainActivity.this, "slide right", Toast.LENGTH_SHORT).show();
                /*if(state==1){
                    state=0;
                    setContentView(R.layout.layout1);
                }*/
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

    }

    @Override
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