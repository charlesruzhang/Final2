package com.example.final2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class Game2Activity extends AppCompatActivity {
    private String TAG = "Game2";

    private LocationManager locationManager;
    private String provider;
    private Context mContext = null;
    /** The handler for location updates sent by the location listener service. */
    private BroadcastReceiver locationUpdateReceiver;
    /** The radial location accuracy required to send a location update. */
    private static final float REQUIRED_LOCATION_ACCURACY = 28f;
    private String locationProvider;
    private final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    /** hint button. **/
    private Button hintButton;
    private final double PROXIMITY = 0.002;
    private final double targetLatitude = 40.10483;
    private final double targetLongitude = -88.227009;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        setTitle("GAME2");


        TextView latitudeText = findViewById(R.id.LatitudeText);
        TextView longigudeText = findViewById(R.id.LongitudeText);
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        /** hint button listener - please go to the showHint method at the end and add hint **/
        hintButton = findViewById(R.id.hintButton);
        hintButton.setOnClickListener(v -> showHint());
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);//低精度，如果设置为高精度，依然获取不了location。
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        //从可用的位置提供器中，匹配以上标准的最佳提供器
        locationProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: 没有权限 ");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        Log.d(TAG, "onCreate: " + (location == null) + "..");
        if (location != null) {
            Log.d(TAG, "onCreate: location");
            //不为空,显示地理位置经纬度
            showLocation(location);
        }
        //监视地理位置变化
        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);

    }

    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle arg2) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d(TAG, "onProviderEnabled: " + provider + ".." + Thread.currentThread().getName());
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "onProviderDisabled: " + provider + ".." + Thread.currentThread().getName());
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged: " + ".." + Thread.currentThread().getName());
            //如果位置发生变化,重新显示
            showLocation(location);
        }
    };

    private void showLocation(Location location) {
        Log.d(TAG,"定位成功------->"+"location------>经度为：" + location.getLatitude() + "\n纬度为" + location.getLongitude());
        TextView latitudeText = findViewById(R.id.LatitudeText);
        TextView longigudeText = findViewById(R.id.LongitudeText);
        boolean bol = checkLocation(location);
        latitudeText.setText("Your Latitude "  + location.getLatitude() + "");
        longigudeText.setText("Your Longitude " + location.getLongitude() + "");
        TextView latiDif = findViewById(R.id.LatitudeDif);
        TextView longiDif = findViewById(R.id.LongitudeDif);
        latiDif.setText(Math.abs(location.getLatitude() - targetLatitude) + "");
        longiDif.setText(Math.abs(location.getLongitude() - targetLongitude) + "");
    }

    private boolean checkLocation(Location location) {
        Log.e("Difference", (location.getLongitude() - targetLongitude) + "");
        if (Math.abs(location.getLongitude() - targetLongitude) < PROXIMITY && Math.abs(location.getLatitude() - targetLatitude) < PROXIMITY) {
            Log.d(TAG, "YEah");
            Pass dialog = new Pass();
            dialog.levelPassed(2);
            dialog.show(getSupportFragmentManager(), "Pass");
            onDestroy();
            return true;
        }
        return false;
    }
    /** enter your hint for this level in this method. */
    private void showHint() {
        Hint dialog = new Hint();
        dialog.addHint("Where's Geoff?");
        dialog.show(getSupportFragmentManager(), "Hint");
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
                return;
            }
        }
    }
}