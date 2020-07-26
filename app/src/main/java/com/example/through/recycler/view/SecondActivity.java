package com.example.through.recycler.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.through.R;
import com.example.through.recycler.presenter.SecondPresenter;

import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class SecondActivity extends MvpAppCompatActivity implements IPosition {

    private ImageView photo;
    String url;

    @InjectPresenter
    SecondPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        photo = findViewById(R.id.imageView);
        url = getIntent().getStringExtra("URL");
        Glide
                .with(this)
                .load(url)
                .into(photo);

    }



    @Override
    public void largeUrl(String url) {

    }

    @Override
    public void setPosition(int pos) {

    }

    @Override
    public void updateRecyclerView() {

    }
}