package com.example.through.recycler.view;

import android.os.Bundle;
import android.util.Log;

import com.example.through.R;
import com.example.through.recycler.presenter.SecondPresenter;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class SecondActivity extends MvpAppCompatActivity implements IPosition {

    @InjectPresenter
    SecondPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



    }


    @Override
    public void setPosition(int pos) {

    }
}