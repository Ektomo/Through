package com.example.through.recycler.app;

import android.app.Application;

import androidx.room.Room;

import com.example.through.recycler.model.AppDatabase;

public class App extends Application {

    private static AppDatabase appDatabase;
    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "rec_database").build();
        appComponent = generateComponent();
    }

    private AppComponent generateComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
