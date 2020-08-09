package com.example.through.recycler.model;


import io.reactivex.Single;

public class RecyclerImage {

    private int counter = 0;
    private Single<Integer> position;

    public Single<Integer> getObservablePosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = Single.just(position);
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

}
