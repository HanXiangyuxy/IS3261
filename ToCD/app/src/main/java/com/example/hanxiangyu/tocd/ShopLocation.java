package com.example.hanxiangyu.tocd;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by HanXiangyu on 21/10/14.
 */
public class ShopLocation extends Activity{

    static final LatLng NUS = new LatLng(1.2956, 103.776);
    static final LatLng OrchardRd = new LatLng(1.3051, 103.831);

    private GoogleMap map;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_location);

        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        if(map == null) System.out.println("map null");

        Marker nus = map.addMarker(new MarkerOptions().position(NUS).title("CD Store 1").snippet("This is CD Store 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.shop_location)));
        Marker orchardRd = map.addMarker(new MarkerOptions().position(OrchardRd).title("CD Store 2").snippet("This is CD Store 2").icon(BitmapDescriptorFactory.fromResource(R.drawable.shop_location)));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(NUS, 20));

        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        Boolean locationEnabled;
        LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if(!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)&&!manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationEnabled = false;
            Toast.makeText(this, "Enable location services for accurate data", Toast.LENGTH_SHORT).show();
        }else locationEnabled = true;

        if(locationEnabled){
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    Marker currentLoc = map.addMarker(new MarkerOptions().position(latLng).title("Current Location")
                            .snippet("This is where you are right now.").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
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


}
