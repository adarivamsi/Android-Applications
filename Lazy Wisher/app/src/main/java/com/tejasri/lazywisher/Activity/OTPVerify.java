package com.tejasri.lazywisher.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tejasri.lazywisher.smsscheduler.R;


public class OTPVerify extends AppCompatActivity {

    SharedPreferences sp;
    EditText verify_otp;
    String otp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpverify);
        sp = getSharedPreferences("LAZY_WISHER",MODE_PRIVATE);
        verify_otp = (EditText)findViewById(R.id.otp_verify);

    }
    public void Verify_Otp(View view){
        otp = verify_otp.getText().toString();
        String otp_receive = sp.getString("OTP_SP",null);
        if (otp.equals(otp_receive)){
            startActivity(new Intent(OTPVerify.this,SmsListActivity.class));
        }else {
         verify_otp.setError("Please enter valid otp");
        }


    }
}
