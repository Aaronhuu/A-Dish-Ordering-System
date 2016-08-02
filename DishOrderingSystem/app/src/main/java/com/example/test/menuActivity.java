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

public class menuActivity extends ListActivity {

    private List<Map<String, Object>> mData;
    private DishDB mDishDB;
    private OrderDB mOrderDB;
    private ArrayList<Dish> dishs= new ArrayList<Dish>();
    private ArrayList<Dish> selected = new ArrayList<Dish>();
    protected final static int MENU_ADD = Menu.FIRST;
    protected final static int MENU_CHECK = Menu.FIRST+1;
    protected final static int MENU_PAY = Menu.FIRST+2;

    private int tablenumber = 4;
    private String vipstate = "Nodiscount";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
        Intent i = getIntent();
        vipstate = i.getStringExtra("VIPstate");

    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        ArrayList<Integer> a = new ArrayList<Integer>();
        Map<String, Object> map = new HashMap<String, Object>();
        mDishDB = new DishDB(this);
        dishs = mDishDB.getAllDishes();
        int k = dishs.size();
        for(int i=0;i<k;i++){
            map = new HashMap<String, Object>();
            map.put("title", dishs.get(i).getDishName());
            map.put("info", dishs.get(i).getPrice());
            map.put("img", dishs.get(i).getURI());
            list.add(map);
        }
        return list;
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {

        Log.v("MyListView4-click", (String) mData.get(position).get("title"));
    }

    public String dishSelected(){

        String selects = "";
        for(Dish d:selected){
            selects = selects+" "+d.getDishName();
        }
        return selects;
    }

    public int totalmoneyselected(){
        int total=0;
        for(Dish d:selected){
            int money = Integer.parseInt(d.getPrice());
            total += money;
        }
        return total;
    }

    public void showInfo(){
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("Those are the item you selected: "+dishSelected()+"                 these are the total cost: "+totalmoneyselected())
                .setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_ADD, 0, "Confirm");
        menu.add(Menu.NONE, MENU_CHECK, 0, "Modify my order");
        menu.add(Menu.NONE,MENU_PAY,0,"Pay");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_ADD:
                confirmSelected();
                break;
            case MENU_CHECK:
                Intent intent = new Intent(menuActivity.this, ModifyOrderActivity.class);
                intent.putExtra("tablenumber",tablenumber);
                startActivity(intent);
                break;

            case MENU_PAY:
                Intent i = new Intent(menuActivity.this, ReceiptActivity.class);
                i.putExtra("tableindex",tablenumber);
                i.putExtra("VIPstate",vipstate);
                startActivity(i);

        }
        return true;
    }

    public void confirmSelected(){
        mOrderDB = new OrderDB(this);
        for(Dish select: selected){
            mOrderDB.insertOrder(tablenumber,select.getDishName(),select.getPrice(),select.getURI(),"Ordered");
        }
        Intent intent = new Intent(menuActivity.this, enterActivity.class);
        startActivity(intent);
        finish();
        //showInfo();
    }

    public final class ViewHolder{
        public ImageView img;
        public TextView title;
        public TextView info;
        public Button viewBtn;
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

                convertView = mInflater.inflate(R.layout.menulist, null);
                holder.img = (ImageView)convertView.findViewById(R.id.img);
                holder.title = (TextView)convertView.findViewById(R.id.title);
                holder.info = (TextView)convertView.findViewById(R.id.info);
                holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
                convertView.setTag(holder);

            }else {

                holder = (ViewHolder)convertView.getTag();
            }


            holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
            holder.title.setText((String) mData.get(position).get("title"));
            holder.info.setText("RMB: "+(String) mData.get(position).get("info"));
            final String dishname = (String) mData.get(position).get("title");
            final String price = (String) mData.get(position).get("info");
            final int pic_uri = (Integer) mData.get(position).get("img");
            holder.viewBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Dish d = new Dish(pic_uri,dishname,price);
                    if(selected.size()==0){
                        selected.add(d);
                    }
                    else{
                        boolean exist = false;
                        for(int i=0;i<selected.size();i++){
                            if(selected.get(i).getDishName().equals(d.getDishName())){
                                selected.remove(i);
                                exist=true;
                                break;
                            }
                        }
                        if(!exist){
                            selected.add(d);
                        }
                    }


                    //showInfo();
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
