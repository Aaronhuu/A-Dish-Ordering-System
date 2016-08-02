package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by aaronhu on 5/14/16.
 */
public class ChangeMenuDetailsActivity extends Activity {
    private DishDB mDishDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_page);
        mDishDB = new DishDB(this);

        Intent intent  = getIntent();
        String dishname = intent.getStringExtra("dishname");
        final Dish d = mDishDB.GetDishbyName(dishname);
        //get the name,price of the dish to change the dish menu
        final TextView viewname = (TextView)findViewById(R.id.dishnameinchangemenu);
        final TextView viewPrice = (TextView)findViewById(R.id.dishpriceinchangemenu);
        final EditText getname = (EditText)findViewById(R.id.newdishname);
        final EditText getprice = (EditText)findViewById(R.id.newdishprice);
        final ImageView viewPic = (ImageView)findViewById(R.id.dishpicinchangemenu);
        Button btnview = (Button)findViewById(R.id.checkresult);
        Button btnchange = (Button)findViewById(R.id.comfirmchange);

        viewname.setText("Name: "+d.getDishName());
        viewPrice.setText("Price: " + d.getPrice());
        viewPic.setBackgroundResource(d.getURI());

        final String name = getname.getText().toString();
        final String price = getprice.getText().toString();

        btnview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(getname.getText().toString().equals("")) {
                    if (getprice.getText().toString().equals("")) {

                    } else {
                        viewPrice.setText("Price: " + getprice.getText().toString());
                    }
                } else {
                    if (getprice.getText().toString().equals("")) {
                        viewname.setText("Name: "+getname.getText().toString());
                    } else {
                        viewname.setText("Name: "+getname.getText().toString());
                        viewPrice.setText("Price: " + getprice.getText().toString());
                    }
                }
            }
        });

        btnchange.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(getname.getText().toString().equals("")){
                    if (getprice.getText().toString().equals("")){

                    }else{
                        Dish dish = new Dish(d.getURI(),d.getDishName(),getprice.getText().toString());
                        mDishDB.UpdateDish(dish,mDishDB.GetDishIDbyName(d.getDishName()));
                        finish();
                    }
                }else{
                    if (getprice.getText().toString().equals("")){

                        Dish dish = new Dish(d.getURI(),getname.getText().toString(),d.getPrice());
                        mDishDB.UpdateDish(dish,mDishDB.GetDishIDbyName(d.getDishName()));
                        finish();

                    }else{

                        Dish dish = new Dish(d.getURI(),getname.getText().toString(),getprice.getText().toString());
                        mDishDB.UpdateDish(dish,mDishDB.GetDishIDbyName(d.getDishName()));
                        finish();

                    }
                }
            }
        });









    }

}
