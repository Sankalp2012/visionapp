package com.grippingstories.visionapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class otpreceiver extends BroadcastReceiver {

    private static EditText e1;
    private static Button b2;
    public void set_otp(EditText e,Button b){
        otpreceiver.e1=e;
        otpreceiver.b2=b;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            switch(status.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    String getotp=message.replaceAll("[^0-9]","");
                    e1.setText(getotp);
                    b2.performClick();
                    //System.out.println(e1.getText().toString());
                    break;
                case CommonStatusCodes.TIMEOUT:
                    break;
            }
        }
    }

}
