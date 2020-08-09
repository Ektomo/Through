package com.example.through.mockito.app;

import android.content.Context;

import androidx.room.Room;

import com.example.through.recycler.model.AppDatabase;
import com.example.through.recycler.model.ImageUrlsDao;
import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.model.entities.ImageUrls;
import com.example.through.recycler.model.retrofit.ApiRequest;

import org.mockito.Mock;
import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestModule {
    @Provides
    public ApiRequest provideApiRequest(){
        return Mockito.mock(ApiRequest.class);
    }
    @Provides
    public AppDatabase provideAppDataBase(){
//        Context context = mock(Context.class);
//        return Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        return mock(AppDatabase.class);
    }
    @Provides
    public RecyclerImage providePosition(){
        return Mockito.mock(RecyclerImage.class);
    }
}
