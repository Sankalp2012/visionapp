package com.grippingstories.visionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.security.AccessController.getContext;

public class loginactivity extends AppCompatActivity {
    Timer timer;
    Long num;
    Intent intent;
    public SpeechRecognizer sr;
    public EditText te;
    public Context context;
    private Button b1;
    public texttospeech tts;
    private static final int SPEECH_REQUEST_CODE = 0; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        te = findViewById(R.id.pno);
        b1 = findViewById(R.id.bt);
        context = getApplicationContext();
        System.out.println("speak your phone number");
        tts = new texttospeech("speak your phone number");
        tts.onlyinit(context);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                displaySpeechRecognizer();
            }
        }, 2300);
    }

    private void displaySpeechRecognizer() {
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        System.out.println("welcome");
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<Integer> results = data.getIntegerArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String str = results.toString();
            System.out.println(str);
            str = str.replaceAll("\\s", "");
            System.out.println(str);
            try {
                num = Long.parseLong(str.substring(1, 11));
                System.out.println(num);
                te.setText(str.substring(1, 11));
                b1.performClick();
            } catch (Exception e) {
                System.out.println(e);
                tts.onlyinit(context);
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        displaySpeechRecognizer();
                    }
                }, 2300);
            }


            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void getotp(View view) {
        getotphere g1=new getotphere(num);
        g1.getotp(this);
    }
}