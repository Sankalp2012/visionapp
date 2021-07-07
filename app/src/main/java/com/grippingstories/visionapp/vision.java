package com.grippingstories.visionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;


public class vision extends AppCompatActivity {

    private CardStackLayoutManager manager;
    private cardStackAdapter adapter;
    int count=0;
    static Context c;
    public List<itemModel> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);
        c=getApplicationContext();
        CardStackView cardStackView = findViewById(R.id.cardview);
        manager = new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                System.out.println("Welcome");
                System.out.println("Welcome");
            }

            @Override
            public void onCardSwiped(Direction direction) {
                if (manager.getTopPosition() == adapter.getItemCount() - items.size()) {
                    paginate();
                }
            }

            @Override
            public void onCardRewound() {
            }

            @Override
            public void onCardCanceled() {
            }

            @Override
            public void onCardAppeared(View view, int position) {
            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(1);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new cardStackAdapter(addList(),c);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<itemModel> old = adapter.getItems();
        List<itemModel> baru = new ArrayList<itemModel>(addList());
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<itemModel> addList() {
        items.add(new itemModel(R.drawable.a1));
        items.add(new itemModel(R.drawable.a2));
        items.add(new itemModel(R.drawable.a3));
        items.add(new itemModel(R.drawable.a4));
        items.add(new itemModel(R.drawable.a5));
        items.add(new itemModel(R.drawable.a6));
        items.add(new itemModel(R.drawable.a7));
        items.add(new itemModel(R.drawable.a8));
        items.add(new itemModel(R.drawable.a12));
        items.add(new itemModel(R.drawable.a23));
        return items;
    }
}