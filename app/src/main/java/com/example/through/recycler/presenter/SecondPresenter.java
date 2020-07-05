package com.example.through.recycler.presenter;

import android.util.Log;

import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.view.IPosition;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SecondPresenter extends MvpPresenter<IPosition> {

    private int position;

    public SecondPresenter(){
    }


    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
