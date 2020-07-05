package com.example.through.recycler.presenter;


import android.widget.Adapter;

import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.view.IPosition;
import com.example.through.recycler.view.RecyclerAdapter;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class RecyclerPresenter extends MvpPresenter<IPosition> {
    private RecyclerImage recyclerImage;

    public RecyclerPresenter(){
        recyclerImage = new RecyclerImage();
    }

    public void onButtonClick(){
        int count = recyclerImage.getCounter();
        count++;
        recyclerImage.setCounter(count);
    }


    public int getCount() {
        return recyclerImage.getCounter();
    }


    public void onRecyclerClick( int pos){
        recyclerImage.setPosition(pos);
        getViewState().setPosition(pos);
    }

}
