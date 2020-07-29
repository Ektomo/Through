package com.example.through.recycler.model.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "table_urls")
public class ImageUrls {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Expose
    @SerializedName("largeImageURL")
    public String largeImageURL;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

}
