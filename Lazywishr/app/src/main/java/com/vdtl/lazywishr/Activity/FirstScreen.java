package com.vdtl.lazywishr.Activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.vdtl.lazywishr.R;

import java.util.Random;

import static android.Manifest.permission.SEND_SMS;

public class FirstScreen extends AppCompatActivity {
    EditText edmobile;
    String mobile,otpDigit;
    private static final int NOTIFY_ME_ID=1337;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        generateRadomNumber();

        edmobile = (EditText)findViewById(R.id.edmobile);

    }
    public void SendSmS(View view){
        mobile = edmobile.getText().toString();
        otpDigit = generateRadomNumber();

        if (mobile.equals("") && mobile == null && mobile.isEmpty() && mobile.length() > 10 &&  mobile.length() < 10){
            edmobile.setError("Please enter valid mobile number");
        }else if (otpDigit.equals("") && otpDigit == null && otpDigit.isEmpty()){
        }else {
            startActivity(new Intent(FirstScreen.this,OTPVerify.class).putExtra("otpDigit",otpDigit));
            Notification(otpDigit);
        }

    }

    private void Notification(String otpDigit) {


    }


    public String generateRadomNumber(){

        Random otp = new Random();
        StringBuilder sb = new StringBuilder();
        for (int count=0;count<=7;count++){
            sb.append(otp.nextInt(10));
        }
        String otps = sb.toString();
        return otps;
    }
}
