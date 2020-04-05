package com.tejasri.lazywisher.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tejasri.lazywisher.smsscheduler.R;

import java.util.Random;

public class FirstScreen extends AppCompatActivity {
    EditText mobile_number;
    String m_num;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    String otp_send;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);

        sp = getSharedPreferences("LAZY_WISHER",MODE_PRIVATE);
        mobile_number = (EditText)findViewById(R.id.mobile_numner_verify);

        generateOTP();
    }

    private String generateOTP() {
        char[] chars = "0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder((100000 + rnd.nextInt(900000)) + "-");
        for (int i = 0; i < 6; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);

        return sb.toString();
    }

    public void Verify_Mobile(View view){
        m_num = mobile_number.getText().toString();
        startActivity(new Intent(FirstScreen.this,OTPVerify.class));

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    otp_send = generateOTP();
                    smsManager.sendTextMessage(m_num, null, "Confirmation message from Lazy Wisher :"+otp_send, null, null);

                    editor = sp.edit();
                    editor.putString("OTP_SP",otp_send);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }

}
