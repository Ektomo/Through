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


public class RecyclerActivity extends MvpAppCompatActivity implements IPosition {
    private RecyclerAdapter adapter;
    private List<Drawable> images;

    @InjectPresenter
    RecyclerPresenter presenter;
    @InjectPresenter
    SecondPresenter secondPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setList();
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerAdapter(images);
        adapter.setListenerCounter(listenerCounter);
        recyclerView.setAdapter(adapter);

    }

    private OnClickListenerCounter listenerCounter = new OnClickListenerCounter() {
        @Override
        public void iterationCounter(View v, int position) {
                presenter.onButtonClick();
                presenter.onRecyclerClick(position);

                startActivity(new Intent(RecyclerActivity.this, SecondActivity.class));
                Log.d("TAG", String.valueOf(presenter.getCount()));
                Log.d("TAG", "Position: " + position);
        }


    };

    private List<Drawable> setList(){
        images = new ArrayList<>();
        images.add(getDrawable(R.drawable.ic_launcher_background));
        images.add(getDrawable(R.drawable.ic_launcher_foreground));
        images.add(getDrawable(R.drawable.ic_launcher_background));
        images.add(getDrawable(R.drawable.ic_launcher_foreground));
        images.add(getDrawable(R.drawable.ic_launcher_background));
        images.add(getDrawable(R.drawable.ic_launcher_foreground));
        return images;
    }


    @Override
    public void setPosition(int pos) {
        secondPresenter.setPosition(pos);
    }
}
