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
 * Created by HanXiangyu on 19/10/14.
 */
public class MusicList extends Activity{
    ListView listView;
    Intent myIntent;
    String[] titles={"QiLiXiang\n(七里香)","B’Day","Opus(十二新作)","Overexposed","Earth to Mars","Call and Response"};
    String[] texts={"Jay Chou","Beyonce","Jay Chou","Maroon 5", "Bruno Mars","Maroon 5"};
    int[] resIds={R.drawable.jay,R.drawable.bday,R.drawable.opus,R.drawable.overexposed,R.drawable.earthmars,R.drawable.maroon1,};
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        final ListView list = (ListView) findViewById(R.id.listView);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Object o = list.getItemAtPosition(position);
                System.out.println("click Album" + position);

                switch (position){
                    case 1:
                        myIntent = new Intent(ctx, OpusDetail.class);
                        startActivity(myIntent);
                        break;
                    default:
                        myIntent = new Intent(ctx, Homepage.class);
                        startActivity(myIntent);
                        break;
                }
            }
        });

        System.out.println("before set adapter");

        list.setAdapter(new ListViewAdapter(titles,texts,resIds));

        System.out.println("after set adapter");

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
            System.out.println("after for loop");
        }

        private View makeItemView(String strTitle, String strText, int resId) {
            LayoutInflater inflater = (LayoutInflater) MusicList.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.song_item, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = (TextView) itemView.findViewById(R.id.ItemTitle);
            title.setText(strTitle);
            TextView text = (TextView) itemView.findViewById(R.id.ItemText);
            text.setText(strText);
            ImageView image = (ImageView) itemView.findViewById(R.id.itemImage);
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
        inflater.inflate(R.menu.music_list_action, menu);
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

            case R.id.btn_gotoSingerList:
                myIntent = new Intent(this, SingerList.class);
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
