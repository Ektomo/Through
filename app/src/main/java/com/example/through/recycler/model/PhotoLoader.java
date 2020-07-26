package com.example.through.recycler.model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoLoader {

    private Context context;

    public PhotoLoader(Context context) {
        this.context = context;
    }

    public void loadImage(String url, ImageView imageView){
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }
}
