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


public class AllocateChefActivity extends ListActivity {

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

    private List<Map<String, Object>> getData() {                   //Set a list to save the information of allocation
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        mOrderDB = new OrderDB(this);
        orders = mOrderDB.getAllOrders();
        for(Order o: orders){
            map = new HashMap<String, Object>();
            map.put("table", o.getTableName());
            //Set table number
            map.put("chefname", o.getChef_name());
            //input the name of the chef


            if(!list.contains(map)){
                list.add(map);
            }
        }

        return list;
    }

    protected void onResume() {
        super.onResume();
        onCreate(null);
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v("MyListView4-click", (String) mData.get(position).get("title"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_CHECK, 0, "Finish");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_CHECK:
                Refresh();
                break;
        }
        return true;
    }

    public void Refresh(){
        finish();
    }

    public final class ViewHolder{
        public TextView table;
        public Button viewBtn;
        public TextView whichchef;
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

        public int k=0;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder = null;
            if (convertView == null) {

                holder=new ViewHolder();

                convertView = mInflater.inflate(R.layout.allocate_chef, null);
                holder.table = (TextView)convertView.findViewById(R.id.allocatetable);
                holder.viewBtn = (Button)convertView.findViewById(R.id.btnviewchef);
                holder.whichchef = (TextView)convertView.findViewById(R.id.cheffortable);



                convertView.setTag(holder);

            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            final int k = position;

            holder.table.setText("TABLE NO." + mData.get(position).get("table"));
            holder.whichchef.setText(""+mData.get(position).get("chefname"));
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(AllocateChefActivity.this, CheflistActivity.class);
                    intent.putExtra("tableindex", Integer.parseInt(mData.get(k).get("table").toString()));
                    startActivity(intent);
                    //finish();
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
