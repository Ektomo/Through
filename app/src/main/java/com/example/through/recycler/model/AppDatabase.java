package com.example.through.recycler.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.through.recycler.model.entities.ImageUrls;

@Database(entities = {ImageUrls.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ImageUrlsDao urlsDao();
}
