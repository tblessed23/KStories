package com.example.android.kstories.loggingin;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.kstories.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> loadAllTasks();

    // Create a Query method named loadTaskById that receives an int id and returns a TaskEntry Object
    // The query for this method should get all the data for that id in the task table
    @Query("SELECT * FROM user WHERE userId = :id")
    LiveData<User> loadStoryById(String id);

    @Insert
    void insertTask(User user);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(User taskEntry);

    @Delete
    void deleteTask(User taskEntry);

    // COMPLETE Create a Query method named loadTaskById that receives an int id and returns a TaskEntry Object
    // The query for this method should get all the data for that id in the task table
    @Query("SELECT * FROM user WHERE userId = :userId")
    LiveData<List<User>> loadTaskById(String userId);
}

