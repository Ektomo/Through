package com.example.through.recycler.app;

import com.example.through.recycler.presenter.RecyclerPresenter;
import com.example.through.recycler.presenter.SecondPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})

public interface AppComponent {

    void inject(RecyclerPresenter presenter);
    void inject(SecondPresenter presenter);
    
}
