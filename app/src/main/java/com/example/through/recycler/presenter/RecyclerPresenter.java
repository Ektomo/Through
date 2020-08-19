package com.example.through.recycler.presenter;


import android.util.Log;

import com.example.through.recycler.model.AppDatabase;
import com.example.through.recycler.model.ImageUrlsDao;
import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.model.entities.ImageUrls;
import com.example.through.recycler.model.entities.Photo;
import com.example.through.recycler.model.retrofit.ApiRequest;
import com.example.through.recycler.view.ISetPhoto;
import com.example.through.recycler.view.UpdateStates;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class RecyclerPresenter extends MvpPresenter<UpdateStates> {
    @Inject
    AppDatabase appDatabase;
    @Inject
    ApiRequest apiRequest;
    @Inject
    RecyclerImage position;
    private RecyclerSource src;
    private List<ImageUrls> imageUrls;
    private ImageUrlsDao imageUrlsDao;
    private long count;

    public RecyclerPresenter() {
        src = new RecyclerSource();
    }

    @Override
    protected void onFirstViewAttach() {
    }

    public void getAllImages() {
        Observable<Photo> single = apiRequest.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
                    imageUrls = photos.imageUrls;
                    loadDatabaseImage();
                    getViewState().updateState();
                }, throwable -> Log.e("TAG", throwable.toString())
        );
    }

    public void loadDatabaseImage() {
        Disposable disposable = imageUrlsDao.insertUrls(imageUrls).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(throwable -> Log.e("TAG", throwable.toString()));
    }


    public void showImage() {
        Disposable disposable = imageUrlsDao.getAllUrls().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(urls -> {
                    imageUrls = urls;
                    getViewState().updateState();
                }, throwable -> Log.e("TAG", throwable.toString()));
    }

    public void getCountDb() {
        imageUrlsDao = appDatabase.urlsDao();
        Disposable disposable = imageUrlsDao.getCount().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            count = aLong;

            if (count == 0) {
                getAllImages();
            } else if (count > 20) {
                deleteAll();
                getAllImages();
            } else {
                showImage();
            }
        }, throwable -> Log.e("TAG", throwable.toString()));
    }

    public void deleteAll() {
        Disposable disposable = imageUrlsDao.deleteAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(throwable -> Log.e("TAG", throwable.toString()));
    }

    public void onButtonClick() {
        int count = position.getCounter();
        count++;
        position.setCounter(count);
    }


    public int getCount() {
        return position.getCounter();
    }

    public void onRecyclerClick(int pos) {
        position.setPosition(pos);
    }

    public RecyclerSource getSrc() {
        return src;
    }

    private class RecyclerSource implements BindRecycler {

        @Override
        public void bindView(ISetPhoto iSetPhoto) {
            iSetPhoto.setPhoto(imageUrls.get(iSetPhoto.getPos()).webformatURL);

        }

        @Override
        public int getItemCount() {
            if (imageUrls != null) {
                return imageUrls.size();
            }
            return 0;
        }


    }
}
