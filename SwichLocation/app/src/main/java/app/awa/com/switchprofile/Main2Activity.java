package app.awa.com.switchprofile;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    BroadcastReceiver broadcastReceiver;
   TextView textView,tv2,tv4,tv5,tv6;
    private static Main2Activity inst;
    private AudioManager myAudioManager;
    private static final int REQUEST_PERMISSIONS = 100;
    boolean boolean_permission;
    app.awa.com.switchprofile.MyService myService;

    String lati,longi;
    app.awa.com.switchprofile.MyDb myDb;
    app.awa.com.switchprofile.EmpInfo info;
    ListView lv;
    SharedPreferences sp;
    String locatonaddress ;
    public static Main2Activity instance() {
        return inst;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
       // lv=(ListView)findViewById(R.id.listView);
        fn_permission();
        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        sp=getSharedPreferences("modefile",MODE_APPEND);
        myService=new app.awa.com.switchprofile.MyService();
        Intent service = new Intent(getApplicationContext(), app.awa.com.switchprofile.MyService.class);
        getApplicationContext().startService(service);
           myDb=new app.awa.com.switchprofile.MyDb(this);

        String ty= String.valueOf(sp.getString("lol","wait"));
        tv2.setText(ty);
        String ltd= String.valueOf(sp.getString("longitude","wait"));
        tv4.setText(ltd);
        tv6.setText(sp.getString("locatonaddress","please wait"));
    }
public void savelocation(View v){
    SharedPreferences.Editor editor=sp.edit();
    editor.putString("lol", lati);
    editor.putString("longitude", longi);
    editor.putString("locatonaddress", locatonaddress);
    editor.commit();
    String ty= String.valueOf(sp.getString("lol",""));
    tv2.setText(ty);
    String ltd= String.valueOf(sp.getString("longitude","wait"));
    tv4.setText(ltd);
     tv6.setText(sp.getString("locatonaddress","please wait"));
}
    public void ring(View v){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ring", "one");
        editor.commit();
        Toast.makeText(Main2Activity.this, ""+sp.getString("ring",""), Toast.LENGTH_LONG).show();
    }
    public void vibrate(View v){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ring", "two");
        editor.commit();
        Toast.makeText(Main2Activity.this, ""+sp.getString("ring",""), Toast.LENGTH_LONG).show();
    }
    public void silent(View v){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("ring", "three");
        editor.commit();
        Toast.makeText(Main2Activity.this, ""+sp.getString("ring",""), Toast.LENGTH_LONG).show();
    }
    private boolean runtime_permission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest
                .permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return true;
        }
        return false;
    }
    private void enable_buttons() {
        startService(new Intent(this, app.awa.com.switchprofile.MyService.class));
    }
    @Override
    public void onStart() {
        super.onStart();
       inst = this;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver==null){
            broadcastReceiver=new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    // textView.append("\n"+intent.getExtras().get("coordinates"));
                    lati = String.valueOf(intent.getExtras().get("Latitude"));
                    longi = String.valueOf(intent.getExtras().get("Longitude"));
                    textView.setText("\n" + intent.getExtras().get("Latitude") + "\n" + intent.getExtras().get("Longitude"));
                  double d1,d2;
                    d1= (double) intent.getExtras().get("Latitude");
                    d2= (double) intent.getExtras().get("Longitude");
                    //
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    try {

                        List<Address> listAddresses = geocoder.getFromLocation(d1, d2, 1);
                        Address obj = listAddresses.get(0);
                        String add = obj.getAddressLine(0);
                        if (null != listAddresses && listAddresses.size() > 0) {
                            //  String u_location = listAddresses.get(0).getAddressLine(0);
                            Address address = (Address) listAddresses.get(0);
                            locatonaddress= address.getSubLocality();
                            tv5.setText(locatonaddress);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    myMode();
                }
            };
        }
        registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
    }

    private void myMode() {
        String myring=sp.getString("ring","");
       // Toast.makeText(Main2Activity.this, ""+myring, Toast.LENGTH_LONG).show();

        String mylongi= tv5.getText().toString();
      //  Toast.makeText(Main2Activity.this, ""+mylongi, Toast.LENGTH_LONG).show();
        if (sp.getString("locatonaddress","").equals(mylongi) && myring.equals("one") ) {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Toast.makeText(Main2Activity.this, "Ring Mode", Toast.LENGTH_LONG).show();
        }
        else if (sp.getString("locatonaddress","").equals(mylongi) && myring.equals("two") ){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            Toast.makeText(Main2Activity.this, "Vibrate Mode", Toast.LENGTH_LONG).show();
        }
        else if (sp.getString("locatonaddress","").equals(mylongi) && myring.equals("three") ){
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
             Toast.makeText(Main2Activity.this, "Silent Mode", Toast.LENGTH_LONG).show();
        }

        else {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
          // Toast.makeText(Main2Activity.this, "Ring Mode", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
        }
    }

    //listener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean_permission = true;
                    enable_buttons();
                } else {
                    Toast.makeText(getApplicationContext(), "Please allow the permission", Toast.LENGTH_LONG).show();
                    runtime_permission();
                }
            }
        }
    }
    private void fn_permission() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

            if ((ActivityCompat.shouldShowRequestPermissionRationale(Main2Activity.this, Manifest.permission.ACCESS_FINE_LOCATION))) {


            } else {
                ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION

                        },
                        REQUEST_PERMISSIONS);

            }
        } else {
            boolean_permission = true;
        }
    }
}
