package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Layout2 extends Activity {
	float x1 = 0;
	float x2 = 0;
	float y1 = 0;
	float y2 = 0;

	private PersonDB mPersonDB;

	public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.layout2);
 		final EditText name = (EditText) findViewById(R.id.editText1);
 		final EditText password = (EditText) findViewById(R.id.editText2);


 		mPersonDB = new PersonDB(this);

        Button login = (Button) findViewById(R.id.login);
        String sn = name.getText().toString();
     	login.setOnClickListener(new OnClickListener() {
     		public void onClick(View v) {

     			String sn = name.getText().toString();
     			String sp = password.getText().toString();

     			password.setText("");
     			String state = null;
     			if (sn.contains("chef")) {
     				state = "chef";
     			} else if (sn.contains("waiter")) {
     				state = "waiter";
     			} else if (sn.contains("manager")) {
     				state = "manager";
     			} else if (sn.contains("special")) {
     				state = "boss";
     			} else if(sn.contains("vip")){
     				state = "vip";
     			}else{
					state = "sadqw";
				}
     			switch (state) {
     				case "chef":

     					if(mPersonDB.check(sn,sp)){
     						Intent i = new Intent(v.getContext(), ChefActivity.class);
     						startActivity(i);
     					}
     					break;

     				case "waiter":
     					if(mPersonDB.check(sn,sp)){
     						Intent i = new Intent(v.getContext(), WaitressActivity.class);
     						startActivity(i);
     					}
     					break;

     				case "manager":
     					if(mPersonDB.check(sn,sp)){
     						Intent i = new Intent(v.getContext(), ManagerActivity.class);
     						startActivity(i);
     					}
     					break;

     				case "boss":
     					if(sn.equals("special")&&sp.equals("67891234")){
     						Intent i = new Intent(v.getContext(), BossActivity.class);
     						startActivity(i);
     					}
     					break;
					case "vip":

						if(mPersonDB.check(sn,sp)){
							String sta = mPersonDB.getVipStatus(sn);
							Intent i = new Intent(v.getContext(), menuActivity.class);
							i.putExtra("VIPstate",sta);

							Toast.makeText(Layout2.this, sta, Toast.LENGTH_SHORT).show();
							startActivity(i);

						}





						break;
     				default:
     					break;
     			}
     		}
     	});
     }

	public boolean onTouchEvent(MotionEvent event) {
		//继承了Activity的onTouchEvent方法，直接监听点击事件
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			//当手指按下的时候
			x1 = event.getX();
			y1 = event.getY();
		}
		if(event.getAction() == MotionEvent.ACTION_UP) {
			//当手指离开的时候
			x2 = event.getX();
			y2 = event.getY();
			if(y1 - y2 > 50) {
				//Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
			} else if(y2 - y1 > 50) {
				//Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
			} else if(x1 - x2 > 50) {
				//Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();

				//Intent intent = new Intent(Layout2.this, MainActivity.class);
				//startActivity(intent);

			} else if(x2 - x1 > 50) {
				//Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                /*if(state==1){
                    state=0;
                    setContentView(R.layout.layout1);
                }*/
				Intent intent = new Intent(Layout2.this, enterActivity.class);
				startActivity(intent);
			}
		}
		return super.onTouchEvent(event);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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
