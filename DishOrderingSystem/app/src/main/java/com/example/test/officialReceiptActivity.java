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

public class officialReceiptActivity extends Activity {
    private OrderDB mOrderDB;
    private ArrayList<Order>orders = new ArrayList<Order>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt_official);

        mOrderDB = new OrderDB(this);
        Intent intent = getIntent();
        final int tablename = intent.getIntExtra("tableindex", 0);
        orders = mOrderDB.getOrdersbyTableIndex(tablename);

        Intent i = getIntent();
        final String vipstate = i.getStringExtra("VIPstate");

        double discount = 1;

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




        //Toast.makeText(ReceiptActivity.this, ""+tablename, Toast.LENGTH_SHORT).show();

        ListView lv = (ListView) findViewById(R.id.MyListView1);

        ArrayList<String> data = new ArrayList<String>();
        for (Order o:orders) {
            String s="";
            String name = o.getDish_name();
            int k = 45-name.length();

            for(int iop = 0;iop<k;iop++){
                s =s+" ";
            }


            data.add("                    "+o.getDish_name()+s+ Integer.parseInt(o.getPrice())*discount);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_listitem, data);
        lv.setAdapter(adapter);

        int height = 0;
        for (int ioo = 0; ioo < orders.size(); ioo++) {
            View item = adapter.getView(ioo, null, lv);
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = height + (lv.getDividerHeight() * (adapter.getCount() - 1));
        lv.setLayoutParams(params);

        TextView totalmoney = (TextView)findViewById(R.id.totalprice1);
        totalmoney.setText("Total money: RMB "+totalmoneyselected()*discount);



        //delete this order from table
        mOrderDB.deleteOrder(tablename);


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

