package com.example.android.kstories.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    //Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();
    private LiveData<List<Favorites>> tasks;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving  the tasks from the Database");
        tasks = database.favoritesDao().loadAllFavorites();
    }

    public LiveData<List<Favorites>> getTasks() {
        return tasks;
    }
}
