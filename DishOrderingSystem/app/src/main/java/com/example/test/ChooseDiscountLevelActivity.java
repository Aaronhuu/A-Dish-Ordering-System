package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by aaronhu on 5/15/16.
 */
public class ChooseDiscountLevelActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_discount);


        Intent intent = getIntent();
        final int tablename = intent.getIntExtra("tableindex", 0);


        Button cu = (Button)findViewById(R.id.btnCU);
        Button sliver = (Button)findViewById(R.id.btnSL);
        Button gold = (Button)findViewById(R.id.btnGD);

        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(ChooseDiscountLevelActivity.this, officialReceiptActivity.class);
                intent.putExtra("tableindex", tablename);
                intent.putExtra("VIPstate","cu");
                startActivity(intent);
                finish();
            }
        });

        sliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChooseDiscountLevelActivity.this, officialReceiptActivity.class);
                intent.putExtra("tableindex", tablename);
                intent.putExtra("VIPstate","sliver");
                startActivity(intent);
                finish();
            }
        });

        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ChooseDiscountLevelActivity.this, officialReceiptActivity.class);
                intent.putExtra("tableindex", tablename);
                intent.putExtra("VIPstate","gold");
                startActivity(intent);
                finish();
            }
        });

    }






}
