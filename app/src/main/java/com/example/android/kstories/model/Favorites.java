package com.example.android.kstories.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorites {


    @PrimaryKey
    @NonNull
    private String id;
    //private int userFavoritesId;
    private String titleFavorites;
    private String urlFavorites;


    @Ignore
    public Favorites(String titleFavorites, String urlFavorites) {
        this.titleFavorites = titleFavorites;
        this.urlFavorites = urlFavorites;
    }

    public Favorites(String id, String titleFavorites, String urlFavorites) {
        this.id = id;
        this.titleFavorites = titleFavorites;
        this.urlFavorites = urlFavorites;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
