package com.example.through.recycler.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.through.R;
import com.example.through.recycler.presenter.RecyclerPresenter;
import com.example.through.recycler.presenter.SecondPresenter;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class RecyclerActivity extends MvpAppCompatActivity implements IPosition {
    private RecyclerAdapter adapter;
    private String largeUrl;

    @InjectPresenter
    RecyclerPresenter presenter;

    @ProvidePresenter
    public RecyclerPresenter providePresenter(){
        return new RecyclerPresenter();
    }

    @InjectPresenter
    SecondPresenter secondPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerAdapter(this, presenter.getSrc());
        adapter.setListenerCounter(listenerCounter);
        recyclerView.setAdapter(adapter);

    }

    private OnClickListenerCounter listenerCounter = new OnClickListenerCounter() {
        @Override
        public void iterationCounter(View v, int position) {
                presenter.onButtonClick();
                presenter.onRecyclerClick(position);
                secondPresenter.setLargeUrl(largeUrl);

                Intent intent = new Intent(RecyclerActivity.this, SecondActivity.class);
                intent.putExtra("URL",largeUrl);
                startActivity(intent);
                Log.d("TAG", String.valueOf(presenter.getCount()));
                Log.d("TAG", "Position: " + position);
        }


    };


    @Override
    public void largeUrl(String url) {
        largeUrl = url;
    }

    @Override
    public void setPosition(int pos) {
        secondPresenter.setPosition(pos);
    }

    @Override
    public void updateRecyclerView() {
        adapter.notifyDataSetChanged();
    }
}
