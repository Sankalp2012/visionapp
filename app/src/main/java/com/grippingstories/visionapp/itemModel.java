package com.grippingstories.visionapp;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

public class itemModel {
    private int image;
    public itemModel(){

    }

    public itemModel(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

}
