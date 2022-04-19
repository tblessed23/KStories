package com.example.android.kstories.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserwithFavorites {

    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "userFavoritesId"
    )
    public List<Favorites> favorites;
}
