package com.example.through.recycler;

import android.app.Application;

import androidx.room.Room;

import com.example.through.recycler.model.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "rec_database").build();
    }

    public static AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
