package com.example.through.mockito.presenters;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;


import androidx.room.Room;


import com.example.through.mockito.app.DaggerTestComponent;
import com.example.through.mockito.app.TestComponent;
import com.example.through.mockito.app.TestModule;
import com.example.through.recycler.app.App;
import com.example.through.recycler.model.AppDatabase;
import com.example.through.recycler.model.ImageUrlsDao;
import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.model.entities.ImageUrls;
import com.example.through.recycler.model.entities.Photo;
import com.example.through.recycler.model.retrofit.ApiRequest;
import com.example.through.recycler.presenter.RecyclerPresenter;
import com.example.through.recycler.view.UpdateStates;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class RecyclerPresenterTest {


    @Mock
    UpdateStates updateStates;

//    @Mock
    ImageUrlsDao dao;
    private RecyclerPresenter recyclerPresenter;


    @BeforeClass
    public static void setupClass() {
        RxJavaPlugins.setIoSchedulerHandler(
                scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(
                scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(
                scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                scheduler -> Schedulers.trampoline());
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        recyclerPresenter = Mockito.spy(new RecyclerPresenter());
    }


    @Test
    public void getUrl_isCorrect() {
        DaggerTestComponent.Builder builder = DaggerTestComponent.builder();
        builder.testModule(new TestModule() {



            @Override
            public ApiRequest provideApiRequest() {

                ApiRequest apiRequest = super.provideApiRequest();
                List<ImageUrls> imageUrls = new ArrayList<>();
                Photo photo = new Photo();
                ImageUrls imageUrl = new ImageUrls();
                imageUrl.webformatURL = "web.ru";
                imageUrl.largeImageURL = "large.ru";
                imageUrls.add(imageUrl);
                imageUrls.add(imageUrl);
                photo.imageUrls = imageUrls;
                Mockito.when(apiRequest.requestServer()).thenReturn(Observable.just(photo));
                return apiRequest;

            }

            @Override
            public AppDatabase provideAppDataBase() {
                AppDatabase appDatabase = super.provideAppDataBase();
//                dao = appDatabase.urlsDao();
                List<ImageUrls> imageUrls = new ArrayList<>();
                ImageUrls imageUrl = new ImageUrls();
                imageUrl.id = 1;
                imageUrl.webformatURL = "web.ru";
                imageUrl.largeImageURL = "large.ru";
                imageUrls.add(imageUrl);
                imageUrls.add(imageUrl);
                List<Long> list = new LinkedList<>();
                list.add(1L);
//                appDatabase.urlsDao().insertUrls(imageUrls);
//                doReturn(list).when(imageUrlsDao.insertUrls(Mockito.any()));
//                Mockito.when(dao.insertUrls(imageUrls)).thenReturn(Single.just(list));
                Mockito.when(appDatabase.urlsDao().getAllUrls()).thenReturn(Single.just(imageUrls));
                Mockito.when(appDatabase.urlsDao().insertUrls(Mockito.anyList())).thenReturn(Single.just(Mockito.anyList()));
//                Mockito.when(dao.insertUrls(Mockito.anyList())).thenReturn()
//                Mockito.doNothing().when(dao.insertUrls(Mockito.anyList()));
                return appDatabase;
            }

        });
        TestComponent component = builder.build();

        component.inject(recyclerPresenter);
        recyclerPresenter.attachView(updateStates);
        recyclerPresenter.getAllImages();
        Mockito.verify(updateStates).largeUrl("large.ru");
    }



}
