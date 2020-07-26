package com.example.through.recycler.model.retrofit;

import com.example.through.recycler.model.entities.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    public Observable<Photo> requestServer(){

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        IApiRequest api = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(IApiRequest.class);

        return api.getPhotos("17646259-0bc993c571b8b9ef6ed476f26").subscribeOn(Schedulers.io());
    }

}
