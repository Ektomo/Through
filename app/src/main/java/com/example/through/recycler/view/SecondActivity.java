package com.example.through.recycler.view;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.through.R;
import com.example.through.recycler.presenter.SecondPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class SecondActivity extends MvpAppCompatActivity implements UpdateStates {

    @InjectPresenter
    SecondPresenter presenter;
    private ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        photo = findViewById(R.id.imageView);
    }


    @Override
    public void largeUrl(String url) {
        Glide
                .with(this)
                .load(url)
                .into(photo);
    }


    @Override
    public void updateRecyclerView() {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}