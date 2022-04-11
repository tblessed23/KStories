package com.example.android.kstories.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.kstories.loggingin.UserViewModel;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    //Constant for logging
    private static final String TAG = UserViewModel.class.getSimpleName();

    private final LiveData<List<Favorites>> tasks;

    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public FavoritesViewModel(AppDatabase database, String taskId) {
        tasks = database.favoritesDao().loadFavoritesById(taskId);
    }


    public LiveData<List<Favorites>> getTasks() {
        return tasks;
    }
}
