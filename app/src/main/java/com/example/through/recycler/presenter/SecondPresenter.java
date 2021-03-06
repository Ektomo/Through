package com.example.through.recycler.presenter;

import android.util.Log;

import com.example.through.recycler.app.App;
import com.example.through.recycler.model.AppDatabase;
import com.example.through.recycler.model.ImageUrlsDao;
import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.view.UpdateStates;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SecondPresenter extends MvpPresenter<UpdateStates> {

    @Inject
    AppDatabase appDatabase;
    @Inject
    RecyclerImage clickPosition;
    private int position;
    private String largeUrl;
    private ImageUrlsDao urlsDao;

    public SecondPresenter() {
        App.getAppComponent().inject(this);
        urlsDao = appDatabase.urlsDao();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getPosition();
        loadImage();
    }


    private void loadImage() {
        Disposable disposable = urlsDao.getLargeUrls().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(strings -> {
                    largeUrl = strings.get(position);
                    getViewState().largeUrl(largeUrl);
                }, throwable -> Log.d("TAG", "load image: " + throwable)
        );
    }

    private void getPosition() {
        Disposable disposable = clickPosition.getObservablePosition().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> position = integer);
    }


}
