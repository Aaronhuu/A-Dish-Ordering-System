package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;

import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ReceiptActivity extends Activity {
    private OrderDB mOrderDB;
    private ArrayList<Order>orders = new ArrayList<Order>();
    private String vipstate = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unofficial_receipt_list);




        mOrderDB = new OrderDB(this);
        Intent intent = getIntent();
        final int tablename = intent.getIntExtra("tableindex", 0);

        Intent i = getIntent();
        vipstate = i.getStringExtra("VIPstate");


        orders = mOrderDB.getOrdersbyTableIndex(tablename);

        ListView lv = (ListView) findViewById(R.id.MyListView);

        ArrayList<String> data = new ArrayList<String>();
        for (Order o:orders) {
            String s="";
            String name = o.getDish_name();
            int k = 45-name.length();

            for(int is = 0;is<k;is++){
                s =s+" ";
            }


            data.add("                    "+o.getDish_name()+s+ o.getPrice());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_listitem, data);
        lv.setAdapter(adapter);

        int height = 0;
        for (int iw = 0; iw < orders.size(); iw++) {
            View item = adapter.getView(iw, null, lv);
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = height + (lv.getDividerHeight() * (adapter.getCount() - 1));
        lv.setLayoutParams(params);
        Button leavecomment = (Button)findViewById(R.id.leavecomment);

        leavecomment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ReceiptActivity.this, LeaveCommentActivity.class);
                intent.putExtra("tableindex", tablename);
                startActivity(intent);
                //finish();
            }

        });

        final TextView totalmoney = (TextView)findViewById(R.id.totalprice);
        totalmoney.setText("Total money: RMB " + totalmoneyselected());

        final TextView finalmoney = (TextView)findViewById(R.id.priceafterdiscount);
        double discount = 1;
        if(!vipstate.equals(null)){
            switch (vipstate){
                case "cu":
                    discount=0.9;
                    break;

                case "sliver":
                    discount=0.85;
                    break;

                case "gold":
                    discount=0.8;
                    break;
            }
        }

        double after = totalmoneyselected()*discount;
        finalmoney.setText("After Discount: RMB " + after);



        Button btnx = (Button) findViewById(R.id.applyvip);
        btnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalmoneyselected() < 200) {
                    Toast.makeText(ReceiptActivity.this, "The minium VIP consume is RMB 200, your comsumption is not enough!", Toast.LENGTH_SHORT).show();
                } else if (totalmoneyselected()<350){
                    Intent intent = new Intent();
                    intent.setClass(ReceiptActivity.this, ApplyVIPActivity.class);
                    intent.putExtra("VIPstate", "cu");
                    startActivity(intent);

                }else if(totalmoneyselected()<500){

                    Intent intent = new Intent();
                    intent.setClass(ReceiptActivity.this, ApplyVIPActivity.class);
                    intent.putExtra("VIPstate", "sliver");
                    startActivity(intent);
                    
                }else{
                    Intent intent = new Intent();
                    intent.setClass(ReceiptActivity.this, ApplyVIPActivity.class);
                    intent.putExtra("VIPstate", "gold");
                    startActivity(intent);

                }
            }
        });



        Button btn1 = (Button) findViewById(R.id.print);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ReceiptActivity.this, officialReceiptActivity.class);
                intent.putExtra("tableindex", tablename);
                intent.putExtra("VIPstate",vipstate);
                startActivity(intent);
                finish();
            }

        });


    }

    public int totalmoneyselected() {
        int total=0;
        for(Order o:orders){
            int money = Integer.parseInt(o.getPrice());
            total += money;
        }
        return total;
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

