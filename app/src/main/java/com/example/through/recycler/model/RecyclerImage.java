package com.example.through.recycler.model;


import io.reactivex.Observable;
import io.reactivex.Single;

public class RecyclerImage {

    private int counter = 0;
    private int position;

    public Single<Integer> getObservablePosition(){
        return Single.create(emitter -> {
            emitter.onSuccess(position);
        });
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCounter() { return counter; }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
