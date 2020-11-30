package com.example.android.kstories.model;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface ProfileuDao {

    @Query("SELECT * FROM profileu ORDER BY updated_at")
    LiveData<List<Profileu>> loadAllProfile();

    @Insert
    void insertTask(Profileu profileuEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Profileu profileuEntry);

    @Delete
    void deleteTask(Profileu profileu);

    // Create a Query method named loadTaskById that receives an int id and returns a TaskEntry Object
    // The query for this method should get all the data for that id in the task table
    @Query("SELECT * FROM profileu WHERE userId = :id")
    LiveData<Profileu> loadProfileById(int id);

}
