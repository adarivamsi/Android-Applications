package com.vdtl.lazywishr.Activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.vdtl.lazywishr.R;


public class SmsSchedulerPreferenceActivity extends PreferenceActivity {

    public static final String PREFERENCE_DELIVERY_REPORTS = "prefSendDeliveryReport";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}