package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BossActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boss_main);
        Button btn1 =(Button)findViewById(R.id.btnbossviewcomment);
        Button btn2 =(Button)findViewById(R.id.btnviewanalysis);

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BossActivity.this, ViewCommentActivity.class);
                startActivity(intent);
                //finish();//
            }

        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(BossActivity.this, ViewanalysisActivity.class);
                startActivity(intent);
                //finish();//
            }

        });
    }
}
