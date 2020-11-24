package com.example.android.kstories.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    LiveData<List<Favorites>> loadAllFavorites();

    @Insert
    void insertFavorites(Favorites favoriteEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorites(Favorites favoriteEntry);

    @Delete
    void deleteFavorites(Favorites favoriteEntry);

    // COMPLETED (1) Create a Query method named loadTaskById that receives an int id and returns a TaskEntry Object
    // The query for this method should get all the data for that id in the task table
    @Query("SELECT * FROM favorites WHERE id = :id")
    LiveData<Favorites> loadTaskById(int id);
}

