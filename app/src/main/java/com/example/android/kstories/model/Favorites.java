package com.example.android.kstories.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorites {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titleFavorites;
    private String urlFavorites;


    @Ignore
    public Favorites(String titleFavorites, String urlFavorites) {
        this.titleFavorites = titleFavorites;
        this.urlFavorites = urlFavorites;
    }

    public Favorites(int id, String titleFavorites, String urlFavorites) {
        this.id = id;
        this.titleFavorites = titleFavorites;
        this.urlFavorites = urlFavorites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleFavorites() {
        return titleFavorites;
    }

    public void setTitleFavorites(String title) {
        this.titleFavorites = titleFavorites;
    }

    public String getUrlFavorites() {return urlFavorites;}
    public void setUrlFavorites(String url) {this.urlFavorites = urlFavorites;}
}
