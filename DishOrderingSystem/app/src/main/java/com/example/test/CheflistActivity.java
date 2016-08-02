package com.example.test;

/**
 * Created by aaronhu on 5/14/16.
 */
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class CheflistActivity extends ListActivity {
    private List<Map<String, Object>> mData;
    private PersonDB mPersonDB;
    private OrderDB mOrderDB;
    int tableindex=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        mPersonDB = new PersonDB(this);
        mOrderDB = new OrderDB(this);
        ArrayList<Person> persons = mPersonDB.getChef();
        Intent intent = getIntent();
        tableindex = intent.getIntExtra("tableindex", 0);


        for(Person person:persons){
            map = new HashMap<String, Object>();
            map.put("chefname", person.getName());
            list.add(map);
        }
        return list;
    }

    // ListView ÖÐÄ³Ïî±»Ñ¡ÖÐºóµÄÂß¼­
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Log.v("MyListView4-click", (String)mData.get(position).get("chefname"));
    }

    public void showInfo(){

    }

    public final class ViewHolder{
        public TextView chefname;
        public Button btnallocate;
    }

    public class MyAdapter extends BaseAdapter{

        private LayoutInflater mInflater;


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

                convertView = mInflater.inflate(R.layout.chef_list, null);
                holder.chefname = (TextView)convertView.findViewById(R.id.chefname);
                holder.btnallocate = (Button)convertView.findViewById(R.id.btnallocate);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            holder.chefname.setText((String)mData.get(position).get("chefname"));
            final int k = position;

            holder.btnallocate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOrderDB.UpdateChefName(tableindex,(String)mData.get(k).get("chefname"));
                    finish();
                }
            });


            return convertView;
        }

    }

}


