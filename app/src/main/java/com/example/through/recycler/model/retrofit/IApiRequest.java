package com.example.through.recycler.model.retrofit;

import com.example.through.recycler.model.entities.Photo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IApiRequest {

    @GET("/api/")
    Observable<Photo> getPhotos(@Query("key") String key);

}
