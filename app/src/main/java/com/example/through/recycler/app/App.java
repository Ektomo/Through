package com.example.through.recycler.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        if (enableLeakCanary()) return;

        appComponent = generateComponent();
    }

    private boolean enableLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return true;
        }
        LeakCanary.install(this);
        return false;
    }

    private AppComponent generateComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
