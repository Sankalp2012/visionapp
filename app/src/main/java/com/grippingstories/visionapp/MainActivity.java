package com.grippingstories.visionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    final boolean state=false;
    private TextView te;
    private texttospeech ttsobj;
    private TextToSpeech mtts;
    public Context context;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("login", MODE_PRIVATE);
        if (sp.getBoolean("logged", false)) {
            Intent i = new Intent(this, vision.class);
            startActivity(i);
        } else {
            te = findViewById(R.id.textview);
            context = getApplicationContext();
            String text = te.getText().toString();
            ttsobj = new texttospeech(text);
            ttsobj.onlyinit(context);
            System.out.println("time to complete the job");
            //mtts.speak(text,TextToSpeech.QUEUE_FLUSH,null);
            timer();
        }
    }
    public void timer(){
        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,loginactivity.class);
                startActivity(intent);
                sp.edit().putBoolean("logged",true).apply();
                finish();
            }
        },2300);
    }

}