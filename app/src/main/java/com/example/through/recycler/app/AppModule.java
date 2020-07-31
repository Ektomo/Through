package com.example.through.recycler.app;

import android.app.Application;
import android.content.Context;

import com.example.through.recycler.model.AppDatabase;
import com.example.through.recycler.model.PhotoLoader;
import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.model.retrofit.ApiRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    public final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    ApiRequest provideApiRequest(){
        return new ApiRequest();
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase(){
       return App.getAppDatabase();
    }

    @Singleton
    @Provides
    RecyclerImage providePosition(){
        return new RecyclerImage();
    }

    @Singleton
    @Provides
    PhotoLoader provideLoader(Context context){
        return new PhotoLoader(context);
    }
}
