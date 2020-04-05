package com.vdtl.lazywishr.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vdtl.lazywishr.AppPrefLaunch;
import com.vdtl.lazywishr.MainActivity;
import com.vdtl.lazywishr.R;

import java.util.Random;


public class OTPVerify extends AppCompatActivity {

    EditText verify_otp;
    String otp,otpValue,otps;
    AppPrefLaunch session;
    String secretPin = "LazyWisherUser";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpverify);

          session = new AppPrefLaunch(getApplicationContext());
            otpValue = generateRadomNumber();
            NotificationHandler(otpValue);


        verify_otp = (EditText)findViewById(R.id.otp_verify);




    }



    private void NotificationHandler(String otpValue) {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, OTPVerify.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Notification from Lazy Wisher ")
                .setContentText("Your otp is : " +otpValue).setSmallIcon(R.drawable.ic_add_sms)
                .setContentIntent(pIntent).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, noti);
    }

    public String generateRadomNumber(){

        Random otp = new Random();
        StringBuilder sb = new StringBuilder();
        for (int count=0;count<=7;count++){
            sb.append(otp.nextInt(10));
        }
        otps = sb.toString();
        return otps;
    }

    public void Verify_Otp(View view){
        otp = verify_otp.getText().toString();
        if (otpValue.equals(otp)){
            session.createLoginSession("Vinod Dirishala", "testemail@gmail.com");
            startActivity(new Intent(OTPVerify.this, MainActivity.class));
            finish();
        }else {
            verify_otp.setError("please enter valid secret code");
        }


    }
}
