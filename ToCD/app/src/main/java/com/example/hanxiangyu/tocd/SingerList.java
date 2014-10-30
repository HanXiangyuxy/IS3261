package com.example.hanxiangyu.tocd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by HanXiangyu on 19/10/14.
 */
public class SingerList extends Activity{
    Intent myIntent;
    Context ctx = this;
    String[] singerTitle= {"Asian Singers", "North American Singers", "Type3", "Type4", "Type5"};
    String[] singerText= {"Pop, R&B", "Text2", "Text3", "Text4", "Text5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singer_catagory);

        final ListView list = (ListView) findViewById(R.id.singer_category);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = list.getItemAtPosition(position);
                System.out.println("click category" + position);

                switch (position){
                    case 0:
                        myIntent = new Intent(ctx, AsianSingers.class);
                        startActivity(myIntent);
                        break;
                    case 1:
                        //go to respective category
                        myIntent = new Intent(ctx, NorthAmericanSingers.class);
                        startActivity(myIntent);
                        break;
                    case 2:
                        //go to respective category
                        break;
                    case 3:
                        //go to respective category
                        break;
                    case 4:
                        //go to respective category
                        break;
                    default:
                        finish();
                        break;
                }
            }
        });

        list.setAdapter(new ListViewAdapter(singerTitle, singerText));

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // go up one level instead of going to top page
        actionBar.setDisplayShowTitleEnabled(true); // don't show activity title
    }

    public class ListViewAdapter extends BaseAdapter {
        View[] itemViews;

        public ListViewAdapter(String[] itemTitles, String[] itemTexts
                               ) {
            itemViews = new View[itemTitles.length];

            for (int i = 0; i < itemViews.length; i++) {
                itemViews[i] = makeItemView(itemTitles[i], itemTexts[i]);
            }
            System.out.println("after for loop");
        }

        private View makeItemView(String strTitle, String strText) {
            LayoutInflater inflater = (LayoutInflater) SingerList.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.category_item, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = (TextView) itemView.findViewById(R.id.CategoryTitle);
            title.setText(strTitle);
            TextView text = (TextView) itemView.findViewById(R.id.CategoryText);
            text.setText(strText);

            return itemView;
        }

        public int getCount() {
            return itemViews.length;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView==null){
                return itemViews[position];
            }
            return convertView;
        }

        public View getItem(int position) {
            return itemViews[position];
        }

        public long getItemId(int position) {
            return position;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.singer_list_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent;

        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.btn_gotoMusicList:
                myIntent = new Intent(this, MusicList.class);
                startActivity(myIntent);
                return true;

            case R.id.btn_gotoHomepage:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMainActivity(View view){
        finish();
    }
}
