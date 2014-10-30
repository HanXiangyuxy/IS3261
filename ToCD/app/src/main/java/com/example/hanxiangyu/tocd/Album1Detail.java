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
public class Album1Detail extends Activity{

    ListView listView;
    String[] titles={"QiLiXiang\n(七里香)","GeQian\n(搁浅)","JieKou\n(借口)","ZhiZhanZhiShang\n(止战之殇)","WaiPo\n(外婆)","YuanYouHui\n(园游会)"};
    int[] ratingRes={R.id.album1rating,R.id.album1rating, R.id.album1rating, R.id.album1rating, R.id.album1rating, R.id.album1rating};
    int[] resIds={R.drawable.listen,R.drawable.listen,R.drawable.listen,R.drawable.listen, R.drawable.listen,R.drawable.listen};
    int[] stopIds={R.drawable.stop,R.drawable.stop,R.drawable.stop,R.drawable.stop, R.drawable.stop,R.drawable.stop};
    ViewGroup ratings;
    ViewGroup starRating;
    CheckBox star;
    private ImageButton btnStart,btnStop;
    private static MediaPlayer mediaPlayer=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album1_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // go up one level instead of going to top page
        actionBar.setDisplayShowTitleEnabled(true); // don't show activity title

        final ListView list = (ListView) findViewById(R.id.album1_Song);

        list.setAdapter(new ListViewAdapter(titles,ratingRes,resIds, stopIds));
    }

    private View.OnClickListener starsListener = new OnClickListener(){
        public void onClick(View view){
            int tag = Integer.valueOf((String) view.getTag());

            for(int i=1; i<=tag; i++){
                star = (CheckBox) starRating.findViewWithTag(String.valueOf(i));
                star.setChecked(true);
            }

            for(int i=tag+1; i<=5; i++){
                star = (CheckBox) starRating.findViewWithTag(String.valueOf(i));
                star.setChecked(false);
            }
        }
    };

    public class ListViewAdapter extends BaseAdapter {
        View[] itemViews;

        public ListViewAdapter(String[] itemTitles, int[] ratingRes,
                               int[] itemImageRes, int[] stopIds) {
            itemViews = new View[itemTitles.length];

            for (int i = 0; i < itemViews.length; i++) {
                itemViews[i] = makeItemView(itemTitles[i], ratingRes[i],
                        itemImageRes[i], stopIds[i], i);
            }
            System.out.println("after for loop");
        }

        private View makeItemView(String strTitle, int ratingId, int resId, int stopId, int tag) {
            LayoutInflater inflater = (LayoutInflater) Album1Detail.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // 使用View的对象itemView与R.layout.item关联
            View itemView = inflater.inflate(R.layout.album1_item, null);

            // 通过findViewById()方法实例R.layout.item内各组件
            TextView title = (TextView) itemView.findViewById(R.id.album1SongName);
            title.setText(strTitle);
            starRating = (ViewGroup) itemView.findViewById(R.id.album1rating);
            for(int i=1; i<=5; i++){
                star = (CheckBox) starRating.findViewWithTag(String.valueOf(i));
                star.setOnClickListener(starsListener);
            }
            starRating.setId(ratingId);
            ImageView image = (ImageView) itemView.findViewById(R.id.album1listenBtn);
            image.setId(tag);
            image.setOnClickListener(new ButtonListener());
            image.setImageResource(resId);
            ImageView image2 = (ImageView) itemView.findViewById(R.id.album1stopBtn);
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
                case 0:
                    try {
                    //    if(mediaPlayer==null){
                        if(mediaPlayer!=null){
                            mediaPlayer.stop();
                            //释放资源
                            mediaPlayer.release();
                        }
                        mediaPlayer= MediaPlayer.create(Album1Detail.this, R.raw.qilixiang);
                        //设置是否循环播放
                        mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
                    //    }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        if(mediaPlayer!=null){
                            mediaPlayer.stop();
                            //释放资源
                            mediaPlayer.release();
                        }
                        mediaPlayer= MediaPlayer.create(Album1Detail.this, R.raw.geqian);
                        //设置是否循环播放
                        mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
                    //    }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                  //      if(mediaPlayer==null){
                        if(mediaPlayer!=null){
                            mediaPlayer.stop();
                            //释放资源
                            mediaPlayer.release();
                        }
                            mediaPlayer= MediaPlayer.create(Album1Detail.this, R.raw.jiekou);

                        //设置是否循环播放
                       mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
                //        }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
              //          if(mediaPlayer==null){
                        if(mediaPlayer!=null){
                            mediaPlayer.stop();
                            //释放资源
                            mediaPlayer.release();
                        }
                            mediaPlayer= MediaPlayer.create(Album1Detail.this, R.raw.zhizhanzhishang);

                        //设置是否循环播放
                        mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
               //         }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                   try {
                  //      if(mediaPlayer==null){
                       if(mediaPlayer!=null){
                           mediaPlayer.stop();
                           //释放资源
                           mediaPlayer.release();
                       }
                        mediaPlayer= MediaPlayer.create(Album1Detail.this, R.raw.waipo);

                        //设置是否循环播放
                        mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
                 //       }
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                      //  if(mediaPlayer==null){
                        if(mediaPlayer!=null){
                            mediaPlayer.stop();
                            //释放资源
                            mediaPlayer.release();
                        }
                            mediaPlayer= MediaPlayer.create(Album1Detail.this, R.raw.yuanyouhui);

                        //设置是否循环播放
                        mediaPlayer.setLooping(true);
                        //设置播放起始点
                        mediaPlayer.seekTo(0);
                        //开始播放
                        mediaPlayer.start();
                        //}
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.album1stopBtn:
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

    public void goToAlbum1Location(View view){
        Intent myIntent;

        myIntent = new Intent(this, Album1Location.class);
        startActivity(myIntent);
    }

    public void goToMainActivity(View view){
        finish();
    }
}
