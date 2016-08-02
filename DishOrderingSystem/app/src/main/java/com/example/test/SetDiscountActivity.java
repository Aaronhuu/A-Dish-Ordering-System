package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ListView;
import android.widget.TextView;


public class SetDiscountActivity extends ListActivity {

    private List<Map<String, Object>> mData;
    private OrderDB mOrderDB;
    private ArrayList<Order> orders= new ArrayList<Order>();
    protected final static int MENU_CHECK = Menu.FIRST;
    protected final static int MENU_QUIT = Menu.FIRST+1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        mOrderDB = new OrderDB(this);
        orders = mOrderDB.getAllOrders();
        for(Order o: orders){
            map = new HashMap<String, Object>();
            map.put("table", o.getTableName());

            if(!list.contains(map)){
                list.add(map);
            }
        }

        return list;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v("MyListView4-click", (String) mData.get(position).get("title"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_CHECK, 0, "finish");
        menu.add(Menu.NONE, MENU_QUIT, 0, "Quit");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_CHECK:
                Refresh();
                break;
            case MENU_QUIT:
                Intent intent = new Intent(SetDiscountActivity.this, enterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    public void Refresh(){
        finish();
    }


    protected void onResume() {
        super.onResume();
        onCreate(null);
    }

    public final class ViewHolder{
        public TextView table;
        public Button viewBtn;
    }

    public class MyAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private int ps=0;


        public MyAdapter(Context context){
            this.mInflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mData.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();

                convertView = mInflater.inflate(R.layout.set_discount, null);
                holder.table = (TextView)convertView.findViewById(R.id.discounttable);
                holder.viewBtn = (Button)convertView.findViewById(R.id.discountbtn);


                //holder.finishcooking = (Switch)convertView.findViewById(R.id.switch1);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            final int k = position;

            holder.table.setText("TABLE NO." + mData.get(position).get("table"));

            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(SetDiscountActivity.this, ChooseDiscountLevelActivity.class);
                    intent.putExtra("tableindex", Integer.parseInt(mData.get(k).get("table").toString()));
                    startActivity(intent);
                    //finish();
                }
            });

            /*
            holder.finishcooking.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    mOrderDB.deleteOrder(k);
                }
            });
*/

            return convertView;
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
            return false;
        }

    }

}
