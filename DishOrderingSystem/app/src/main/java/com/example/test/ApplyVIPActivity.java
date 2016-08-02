package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by aaronhu on 5/15/16.
 */
public class ApplyVIPActivity extends Activity{

    private int verificationcode=0;
    private PersonDB mPersonDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_vip);

        final EditText phonenumber = (EditText)findViewById(R.id.phonenumber);  //save the phneno. of the vip
        final EditText password = (EditText)findViewById(R.id.vippassword);     //save the password of the vip
        final EditText verpassword = (EditText)findViewById(R.id.confirmpwd);   //read the verpassword
        final EditText vercode = (EditText)findViewById(R.id.vercode);
        final TextView vet = (TextView)findViewById(R.id.verificodetext);

        Intent i = getIntent();
        final String viPstate = i.getStringExtra("VIPstate");

        Random r = new Random();
        verificationcode =1000+r.nextInt(9000);
        vet.setText(""+verificationcode);

        mPersonDB = new PersonDB(this);



        Button getVerificationCode = (Button)findViewById(R.id.btnverification);
        Button submit = (Button)findViewById(R.id.btnregister);

        getVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                verificationcode =1000+r.nextInt(9000);
                vercode.setText("");
                vet.setText(""+verificationcode);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check phone number whether it is fit the normal phone number
                if(phonenumber.getText().toString().length()==11){
                    //check password
                    if(password.getText().toString().equals(verpassword.getText().toString())&&!password.getText().toString().equals("")){
                        //check verification code
                        if(vercode.getText().toString().equals(""+verificationcode)){
                            //check whether the phone number have already been registered
                            if(!mPersonDB.checkexist("vip"+phonenumber.getText())){
                                //import this VIP account
                                Toast.makeText(ApplyVIPActivity.this, "Your new VIP Account will be: vip"+phonenumber.getText().toString(), Toast.LENGTH_SHORT).show();
                                mPersonDB.insert("vip" + phonenumber.getText().toString(), password.getText().toString(),viPstate);
                                finish();
                            }else{
                                Toast.makeText(ApplyVIPActivity.this, "This account have already exist!", Toast.LENGTH_SHORT).show();
                            }
                        }else{

                            Toast.makeText(ApplyVIPActivity.this, "Not correct verification code!", Toast.LENGTH_SHORT).show();
                            Random r = new Random();
                            verificationcode = r.nextInt(10000);
                            vet.setText(""+verificationcode);
                            vercode.setText("");
                        }
                    }
                    else{
                        Toast.makeText(ApplyVIPActivity.this, "Not the same password!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ApplyVIPActivity.this, "The phone Numner have to be 11 digits!", Toast.LENGTH_SHORT).show();
                }




                //finish();
            }
        });

    }






}
