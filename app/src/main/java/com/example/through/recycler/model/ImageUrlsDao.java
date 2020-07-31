package com.example.through.recycler.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.through.recycler.model.entities.ImageUrls;
import com.example.through.recycler.model.entities.Photo;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ImageUrlsDao {


    @Query("SELECT * FROM table_urls")
    Single<List<ImageUrls>> getAllUrls();

    @Query("SELECT largeImageURL FROM table_urls")
    Single<List<String>> getLargeUrls();

    @Insert
    Single<List<Long>> insertUrls(List<ImageUrls> urls);

    @Query("SELECT COUNT() FROM table_urls")
    Single<Long> getCount();

    @Query("DELETE FROM table_urls")
    Single<Integer> deleteAll();
}
