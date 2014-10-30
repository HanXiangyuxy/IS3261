package com.example.hanxiangyu.tocd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by HanXiangyu on 19/10/14.
 */
public class OpusDetail extends Activity{

    ListView listView;
    String[] titles={"Song Name1","Song Name2","Song Name3","Song Name4"};
    //int[] ratingRes={R.id.rating,R.id.rating, R.id.rating, R.id.rating};
    int[] resIds={R.drawable.listen,R.drawable.listen,R.drawable.listen,R.drawable.listen};
    int[] stopIds={R.drawable.stop,R.drawable.stop,R.drawable.stop,R.drawable.stop};
    ViewGroup ratings;
    ViewGroup starRating;
    CheckBox star;
    private ImageButton btnStart,btnStop;
    private static MediaPlayer mediaPlayer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opus_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // go up one level instead of going to top page
        actionBar.setDisplayShowTitleEnabled(true); // don't show activity title

        final ListView list = (ListView) findViewById(R.id.opusSong);

        list.setAdapter(new ListViewAdapter(titles,resIds, stopIds));
    }

    private View.OnClickListener starsListener = new OnClickListener(){
        public void onClick(View view){
            int tag = Integer.valueOf((String) view.getTag());

            for(int i=1; i<=tag; i++){
                System.out.println("check star: "+i);
                star = (CheckBox) starRating.findViewWithTag(String.valueOf(i));
                star.setChecked(true);
            }

            for(int i=tag+1; i<=5; i++){
                System.out.println("uncheck star: "+i);
                star = (CheckBox) starRating.findViewWithTag(String.valueOf(i));
                star.setChecked(false);
            }
        }
    };

    public class ListViewAdapter extends BaseAdapter {
        View[] itemViews;

        public ListViewAdapter(String[] itemTitles,
                               int[] itemImageRes, int[] stopIds) {
            itemViews = new View[itemTitles.length];

            for (int i = 0; i < itemViews.length; i++) {
                itemViews[i] = makeItemView(itemTitles[i],
                        itemImageRes[i], stopIds[i], i);
            }
            System.out.println("after for loop");
        }

        private View makeItemView(String strTitle, int resId, int stopId, int tag) {
            LayoutInflater inflater = (LayoutInflater) OpusDetail.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.opus_item, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = (TextView) itemView.findViewById(R.id.opusSongName);
            title.setText(strTitle);
            /*starRating = (ViewGroup) itemView.findViewById(R.id.rating);
            for(int i=1; i<=5; i++){
                System.out.println("for star: "+i);
                star = (CheckBox) starRating.findViewWithTag(String.valueOf(i));
                star.setOnClickListener(starsListener);
            }
            starRating.setId(ratingId);*/
            ImageView image = (ImageView) itemView.findViewById(R.id.listenBtn);
            image.setId(tag);
            image.setOnClickListener(new ButtonListener());
            image.setImageResource(resId);
            ImageView image2 = (ImageView) itemView.findViewById(R.id.stopBtn);
            image2.setOnClickListener(new ButtonListener());
            image2.setImageResource(stopId);

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

    class ButtonListener implements OnClickListener{

        public void onClick(View v) {
            switch(v.getId()){
                case 1:
                    System.out.println("in btn" + v.getId());
                    if(mediaPlayer==null){
                         mediaPlayer= MediaPlayer.create(OpusDetail.this, R.raw.test);
                    }
                    try {
                        //设置是否循环播放
                        mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.stopBtn:
                    if(mediaPlayer!=null){
                        //停止播放
                        mediaPlayer.stop();
                        //释放资源
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    break;
            }
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
                myIntent = new Intent(this, Homepage.class);
                startActivity(myIntent);
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
