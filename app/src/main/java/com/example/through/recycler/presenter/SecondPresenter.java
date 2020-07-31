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

    private int position;
    private String largeUrl;
    private ImageUrlsDao urlsDao;

    @Inject
    AppDatabase appDatabase;
    @Inject
    RecyclerImage clickPosition;

    public SecondPresenter(){
        App.getAppComponent().inject(this);
        urlsDao = appDatabase.urlsDao();
        position = clickPosition.getPosition();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadImage();
    }


    public void loadImage(){
        Disposable disposable = urlsDao.getLargeUrls().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(strings -> {
                largeUrl = strings.get(position);
                getViewState().largeUrl(largeUrl);
            }, throwable -> Log.d("TAG", "load image: " + throwable)
        );
    }


}
