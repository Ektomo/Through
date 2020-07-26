package com.example.through.recycler.presenter;

import android.util.Log;

import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.model.entities.Photo;
import com.example.through.recycler.view.IPosition;

import io.reactivex.Observable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SecondPresenter extends MvpPresenter<IPosition> {

    private int position;
    private String largeUrl;

    public SecondPresenter(){
    }


    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }
}
