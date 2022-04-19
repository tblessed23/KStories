package com.example.android.kstories.loggingin;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Database;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.MainViewModel;

import org.jetbrains.annotations.NotNull;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final String mTaskId;


    // Initialize the member variables in the constructor with the parameters received
    public  UserViewModelFactory(AppDatabase database, String taskId) {
        mDb = database;
        mTaskId = taskId;


    }

    // Uncomment the following method
    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new UserViewModel(mDb, mTaskId);
    }


}
