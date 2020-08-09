package com.example.through.mockito.app;


import com.example.through.recycler.presenter.RecyclerPresenter;
import com.example.through.recycler.presenter.SecondPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {
    void inject(RecyclerPresenter recyclerPresenter);
//    void inject(SecondPresenter secondPresenter);
}
