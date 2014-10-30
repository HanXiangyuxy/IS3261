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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by HanXiangyu on 21/10/14.
 */
public class NorthAmericanSingers extends Activity{
    Intent myIntent;
    String[] titles={"Bruno Mars","Maroon 5","Beyonce","Jason Mraz"};
    String[] texts={"Style: R&B","brief intro","also intro","please fill up Zhong Er"};
    int[] resIds={R.drawable.mars,R.drawable.maroon5,R.drawable.beyonce,R.drawable.jason};
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.north_american_singer_list);

        final ListView list = (ListView) findViewById(R.id.north_american_list);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = list.getItemAtPosition(position);
                System.out.println("click Album" + position);

                switch (position){
                    case 0:
                        myIntent = new Intent(ctx, JayDetail.class);
                        startActivity(myIntent);
                        break;
                    default:
                        myIntent = new Intent(ctx, Homepage.class);
                        startActivity(myIntent);
                        break;
                }
            }
        });

        list.setAdapter(new ListViewAdapter(titles,texts,resIds));

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // go up one level instead of going to top page
        actionBar.setDisplayShowTitleEnabled(true); // don't show activity title
    }

    public class ListViewAdapter extends BaseAdapter {
        View[] itemViews;

        public ListViewAdapter(String[] itemTitles, String[] itemTexts,
                               int[] itemImageRes) {
            itemViews = new View[itemTitles.length];

            for (int i = 0; i < itemViews.length; i++) {
                itemViews[i] = makeItemView(itemTitles[i], itemTexts[i],
                        itemImageRes[i]);
            }
        }

        private View makeItemView(String strTitle, String strText, int resId) {
            LayoutInflater inflater = (LayoutInflater) NorthAmericanSingers.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.north_american_item, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = (TextView) itemView.findViewById(R.id.northAmericanTitle);
            title.setText(strTitle);
            TextView text = (TextView) itemView.findViewById(R.id.northAmericanText);
            text.setText(strText);
            ImageView image = (ImageView) itemView.findViewById(R.id.northAmericanImage1);
            image.setImageResource(resId);

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

            case R.id.btn_gotoHomepage:
                finish();
                return true;

            case R.id.btn_gotoMusicList:
                myIntent = new Intent(this, MusicList.class);
                startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToMainActivity(View view){
        finish();
    }
}
