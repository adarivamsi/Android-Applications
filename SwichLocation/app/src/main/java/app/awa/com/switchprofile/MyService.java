package app.awa.com.switchprofile;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    public static final long INTERVAL=10000;//variable to execute services every 10 second
    private Handler mHandler=new Handler(); // run on another Thread to avoid crash
    private Timer mTimer=null; // timer handling
    private LocationListener locationListener;
    private LocationManager locationManager;
    WifiManager wifiManager;
  public static   String  strme;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("unsupported Operation");
    }
    @Override
    public void onCreate() {

        wifiManager=(WifiManager)this.getSystemService(WIFI_SERVICE);
        // cancel if service is  already existed
       // passmyNumber();
       /* locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent intent=new Intent("location_update");
                intent.putExtra("Latitude",location.getLatitude());
                intent.putExtra("Longitude",location.getLongitude());
                intent.putExtra("ph",strme);
                sendBroadcast(intent);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent=new Intent(Settings.ACTION_LOCALE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } ;
        locationManager=(LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,3,locationListener);*/

    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        if(mTimer!=null)
            mTimer.cancel();
        else
            mTimer=new Timer(); // recreate new timer
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(),0,INTERVAL);// schedule task
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent intent=new Intent("location_update");
                intent.putExtra("Latitude",location.getLatitude());
                intent.putExtra("Longitude",location.getLongitude());
               //intent.putExtra("kk",strme);
                sendBroadcast(intent);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent=new Intent(Settings.ACTION_LOCALE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } ;
        locationManager=(LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        if (wifiManager.isWifiEnabled()){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,3000,30,locationListener);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,30,locationListener);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "In Destroy", Toast.LENGTH_SHORT).show();//display toast when method called
        mTimer.cancel();//cancel the timer
    }
    //inner class of TimeDisplayTimerTask
    private class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    // display toast at every 10 second
                  //  Toast.makeText(getApplicationContext(), "Notify", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }




}