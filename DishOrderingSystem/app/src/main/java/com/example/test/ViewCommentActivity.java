package com.example.test;

/**
 * Created by aaronhu on 5/14/16.
 */
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ViewCommentActivity extends ListActivity {
    private List<Map<String, Object>> mData;
    private CommentDB mCommentDB;
    protected final static int MENU_CHECK = Menu.FIRST;
    protected final static int MENU_QUIT = Menu.FIRST+1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCommentDB = new CommentDB(this);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();

        ArrayList<Comment> comments = mCommentDB.getAllComments();
        for(Comment c:comments){
            map = new HashMap<String, Object>();
            map.put("commenttext", c.getPerson_name());
            list.add(map);
        }
        return list;
    }

    // ListView ÖÐÄ³Ïî±»Ñ¡ÖÐºóµÄÂß¼­

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Log.v("MyListView4-click", (String) mData.get(position).get("commenttext"));
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_CHECK, 0, "finish");
        //menu.add(Menu.NONE, MENU_QUIT, 0, "Quit");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_CHECK:
                finish();
                break;

        }
        return true;
    }




    public void showInfo(){

    }



    public final class ViewHolder{
        public TextView commenttext;
        public LinearLayout commentlayout;
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

                convertView = mInflater.inflate(R.layout.view_comment, null);
                holder.commenttext = (TextView)convertView.findViewById(R.id.commenttext);
                holder.commentlayout = (LinearLayout)convertView.findViewById(R.id.commentlayout);
                convertView.setTag(holder);

            }else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.commenttext.setText((String)mData.get(position).get("commenttext"));

            holder.commentlayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent();
                    //intent.setClass(ViewcommentActivity.this, CommentdetailActivity.class);
                    //startActivity(intent);
                }
            });
            return convertView;
        }

    }
}

