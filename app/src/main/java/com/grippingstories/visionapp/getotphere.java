package com.grippingstories.visionapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class getotphere {
    Long pn;
    String pno;
    long l;
    Intent i;
    String otp;
    getotphere(Long number){
        pn=number;
        pno=Long.toString(pn);
        l=60;
    }

    public void getotp(Context context) {
        try {
            Activity activity=(Activity) context;
            PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + pno, 60, TimeUnit.SECONDS, activity, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    System.out.println("starting failed activity" + e);
                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    //otp=s;
                    //System.out.println("creation complete");
                    //System.out.println(pno);
                    Intent i=new Intent(activity,getotp.class);
                    i.putExtra("pno","+91"+pno);
                    i.putExtra("otp",s);
                    context.startActivity(i);
                }
            });
        }
        catch (Exception e) {
            System.out.println(e);
        }
        //finally {
          //  return otp;
        //}
        }
}