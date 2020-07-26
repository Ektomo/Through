package com.example.through.recycler.presenter;


import android.util.Log;

import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.model.entities.Hit;
import com.example.through.recycler.model.entities.Photo;
import com.example.through.recycler.model.retrofit.ApiRequest;
import com.example.through.recycler.view.IPosition;
import com.example.through.recycler.view.ISetPhoto;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class RecyclerPresenter extends MvpPresenter<IPosition> {
    private RecyclerImage recyclerImage;
    private RecyclerSource src;
    private List<Hit> imageUrls;
    private ApiRequest api;

    public RecyclerPresenter(){
        src = new RecyclerSource();
        recyclerImage = new RecyclerImage();
        api = new ApiRequest();
    }

    @Override
    protected void onFirstViewAttach() {
        getAllImages();
    }

    public void getAllImages(){
        Observable<Photo> single = api.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photos -> {
            imageUrls = photos.hits;


            getViewState().updateRecyclerView();
        },throwable -> {
                    Log.e("TAG", throwable.toString());
                }
                );
    }

    public void onButtonClick(){
        int count = recyclerImage.getCounter();
        count++;
        recyclerImage.setCounter(count);
    }


    public int getCount() {
        return recyclerImage.getCounter();
    }



    private class RecyclerSource implements BindRecycler{

        @Override
        public void bindView(ISetPhoto iSetPhoto) {
            iSetPhoto.setPhoto(imageUrls.get(iSetPhoto.getPos()).webformatURL);

        }

        @Override
        public int getItemCount() {
            if(imageUrls != null) {
                return imageUrls.size();
            }
            return 0;
        }


    }

    public void onRecyclerClick( int pos){
        recyclerImage.setPosition(pos);
        getViewState().setPosition(pos);
        getViewState().largeUrl(imageUrls.get(pos).largeImageURL);
    }

    public RecyclerSource getSrc() {
        return src;
    }
}
