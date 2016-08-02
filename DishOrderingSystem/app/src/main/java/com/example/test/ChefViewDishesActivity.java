package com.example.test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChefViewDishesActivity extends ListActivity {

    private List<Map<String, Object>> mData;
    private DishDB mDishDB;
    private OrderDB mOrderDB;
    private ArrayList<Order>orders = new ArrayList<Order>();
    private ArrayList<Dish> selected = new ArrayList<Dish>();
    private long k=0;
    //private int tableindex=0;
    protected final static int MENU_CHECK = Menu.FIRST;
    protected final static int MENU_QUIT = Menu.FIRST+1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
        //Intent intent = getIntent();
        //tableindex = intent.getIntExtra("tableindex", 0);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        mOrderDB=new OrderDB(this);
        Intent intent = getIntent();
        int tablename = intent.getIntExtra("tableindex", 0);
        orders = mOrderDB.getOrdersbyTableIndex(tablename);

        for(Order o: orders){
            if(!o.getDish_status().equals("finish")){
                map = new HashMap<String, Object>();
                map.put("title", o.getDish_name());
                map.put("img", o.getDish_pic());
                map.put("status",o.getDish_status());
                map.put("chefname",o.getChef_name());
                list.add(map);
            }
        }

        return list;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        k = id;
        Log.v("MyListView4-click", (String) mData.get(position).get("title"));
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_CHECK, 0, "Finish");
        //menu.add(Menu.NONE, MENU_QUIT, 0, "Quit");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_CHECK:
                confirmSelected();
                break;
        }
        return true;
    }

    public void confirmSelected(){
        //Intent intent = new Intent(ChefViewDishesActivity.this, ChefActivity.class);
        //startActivity(intent);
        finish();
        //showInfo();
    }

    public final class ViewHolder{
        public ImageView img;
        public TextView title;
        public Button viewBtn;
        public TextView status;
        public TextView chefname;
    }

    public class MyAdapter extends BaseAdapter{

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
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();

                convertView = mInflater.inflate(R.layout.chef_dishlist, null);
                holder.img = (ImageView)convertView.findViewById(R.id.img1);
                holder.title = (TextView)convertView.findViewById(R.id.title1);
                holder.status = (TextView)convertView.findViewById(R.id.status_cooking);
                holder.viewBtn = (Button)convertView.findViewById(R.id.checkbtn);
                holder.chefname =(TextView)convertView.findViewById(R.id.chefforthistable);
                convertView.setTag(holder);

            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("title"));
            holder.status.setText((String)mData.get(position).get("status"));
            holder.chefname.setText((String)mData.get(position).get("chefname"));

            final ViewHolder finalHolder = holder;
            final int ks= position;

            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderDB.UpdateStatus(mOrderDB.getIDbyname(mData.get(position).get("title").toString()),"Cooking");
                    finalHolder.status.setText("Cooking");
                }
            });



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
