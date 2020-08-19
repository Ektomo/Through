package com.example.through.recycler.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.through.R;
import com.example.through.recycler.app.App;
import com.example.through.recycler.presenter.RecyclerPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class RecyclerActivity extends MvpAppCompatActivity implements UpdateStates {
    @InjectPresenter
    RecyclerPresenter presenter;
    private RecyclerAdapter adapter;
    private OnClickListenerCounter listenerCounter = new OnClickListenerCounter() {
        @Override
        public void iterationCounter(View v, int position) {
            presenter.onButtonClick();
            presenter.onRecyclerClick(position);
            Intent intent = new Intent(RecyclerActivity.this, SecondActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            Log.d("TAG", String.valueOf(presenter.getCount()));
            Log.d("TAG", "Position: " + position);
        }


    };

    @ProvidePresenter
    public RecyclerPresenter providePresenter() {
        RecyclerPresenter recyclerPresenter = new RecyclerPresenter();
        App.getAppComponent().inject(recyclerPresenter);
        return recyclerPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        presenter.getCountDb();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerAdapter(this, presenter.getSrc());
        adapter.setListenerCounter(listenerCounter);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void largeUrl(String url) {
    }

    @Override
    public void updateState() {
        adapter.notifyDataSetChanged();
    }
}
