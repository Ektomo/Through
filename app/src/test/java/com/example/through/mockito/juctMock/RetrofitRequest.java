package com.example.through.mockito.juctMock;

import com.example.through.recycler.app.App;
import com.example.through.recycler.model.AppDatabase;
import com.example.through.recycler.model.ImageUrlsDao;
import com.example.through.recycler.model.entities.ImageUrls;
import com.example.through.recycler.model.entities.Photo;
import com.example.through.recycler.model.retrofit.ApiRequest;
import com.example.through.recycler.presenter.RecyclerPresenter;
import com.example.through.recycler.view.UpdateStates;
import com.google.gson.internal.$Gson$Preconditions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RetrofitRequest {

    @Mock
    ApiRequest api;

    @Mock
    AppDatabase db;

    @Mock
    ImageUrlsDao dao;

    @Mock
    UpdateStates updateStates;

    @InjectMocks
    RecyclerPresenter presenter;

    @Before
    public void setupInit(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                scheduler -> Schedulers.trampoline());
    }


    @Test
    public void request_isCorrect(){

        Photo photo = new Photo();
        List<ImageUrls> imageUrls = new ArrayList<>();
        ImageUrls imageUrl = new ImageUrls();
        imageUrl.webformatURL = "web.ru";
        imageUrl.largeImageURL = "large.ru";
        imageUrls.add(imageUrl);
        imageUrls.add(imageUrl);
        photo.imageUrls = imageUrls;
        Mockito.when(api.requestServer()).thenReturn(Observable.just(photo));
        List<Long> list = new ArrayList<>();
        list.add(1L);
        Single result = Single.just(photo.imageUrls);
        Mockito.when(dao.getAllUrls()).thenReturn(result);
//        presenter.attachView(updateStates);
//        presenter.getAllImages();
//        Assert.assertEquals(dao.getAllUrls(), result);
        presenter.attachView(updateStates);
        presenter.getAllImages();
        Mockito.verify(updateStates).largeUrl("large.url");
    }

    @Test
    public void request_verify(){
        dao.getAllUrls();
        Mockito.verify(dao).getAllUrls();
        dao.insertUrls(Mockito.anyList());
        Mockito.verify(dao).insertUrls(Mockito.anyList());
        Mockito.verify(presenter).attachView(updateStates);
    }

//    @Test
//    public void presenter_isCorrect(){
//        List<ImageUrls> imageUrls = new ArrayList<>();
//        ImageUrls imageUrl = new ImageUrls();
//        imageUrl.webformatURL = "web.ru";
//        imageUrl.largeImageURL = "large.ru";
//        imageUrls.add(imageUrl);
//        imageUrls.add(imageUrl);
//        Mockito.when(presenter.getAllImages()).thenReturn();
//    }
}
