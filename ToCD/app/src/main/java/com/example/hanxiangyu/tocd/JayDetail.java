package com.example.hanxiangyu.tocd;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by HanXiangyu on 21/10/14.
 */
public class JayDetail extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jay_detail);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true); // go up one level instead of going to top page
        actionBar.setDisplayShowTitleEnabled(true); // don't show activity title
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.homepage_actions, menu);
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

            case R.id.btn_gotoSingerList:
                myIntent = new Intent(this, SingerList.class);
                startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void goToAlbum1Detail(View view){
        Intent myIntent;
        myIntent = new Intent(this, Album1Detail.class);
        startActivity(myIntent);
    }

    public void gotoOpusDetail(View view){
        Intent myIntent;
        myIntent = new Intent(this, OpusDetail.class);
        startActivity(myIntent);
    }

    //Intent   myIntent;

    public void goToMainActivity(View view){
        finish();
    }
}
