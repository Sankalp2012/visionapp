package com.grippingstories.visionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class getotp extends AppCompatActivity {

    EditText e1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getotp);
        e1 = findViewById(R.id.otp);
        b1=findViewById(R.id.button);
        String onetp;
        SmsRetrieverClient client= SmsRetriever.getClient(this);
        //System.out.println("creating client");
        Task<Void> task=client.startSmsRetriever();
        //System.out.println("creating task");
        task.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //System.out.println("executing task");
                new otpreceiver().set_otp(e1,b1);
            }
        });
    }

    public void verify(View view) {
        String pno=getIntent().getStringExtra("pno");
        String otp = getIntent().getStringExtra("otp");
        try {
            PhoneAuthCredential p1 = PhoneAuthProvider.getCredential(otp, e1.getText().toString());
            FirebaseAuth.getInstance().signInWithCredential(p1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseDatabase fd=FirebaseDatabase.getInstance();
                            DatabaseReference dr=fd.getReference("users");
                            FirebaseAuth a1=FirebaseAuth.getInstance();
                            FirebaseUser currentUser = a1.getCurrentUser();
                            String uid=currentUser.getUid();
                            user u=new user();
                            u.setPno(Long.parseLong(pno));
                            u.setUser_id(uid);
                            dr.setValue(u);
                            Intent i=new Intent(getApplicationContext(),vision.class);
                            startActivity(i);
                        }
                    });
        } catch (Exception e) {
            System.out.println("cannot create phoneauthcredential"+e);
        }
    }
}
