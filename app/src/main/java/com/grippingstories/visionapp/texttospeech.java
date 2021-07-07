package com.grippingstories.visionapp;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;

import java.util.Locale;
import android.os.Handler;

public class texttospeech {
    private TextToSpeech tts;
    private String t1;
    public String state;
    texttospeech(String text) {
        t1=text;
        System.out.println(text);
    }
    public void onlyinit(Context context) {
        System.out.println("only init called");
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                System.out.println("inside inner class");
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "language not supported");
                    } else {
                    }
                } else {
                    Log.e("TTS", "inititalization failed");
                }
            }

        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("inside handler");
                tts.speak(t1, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println("inside handler");
            }
        }, 2000);
    }
    /*
    public void speak(){
        tts.speak(t1,TextToSpeech.QUEUE_FLUSH,null);
        System.out.println("speak action performed");
    }
*/
}
