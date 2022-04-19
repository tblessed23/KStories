package com.example.android.kstories.user;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.UserEditViewModel;

import org.jetbrains.annotations.NotNull;

public class StoryViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final String mTaskId;


    // Initialize the member variables in the constructor with the parameters received
    public  StoryViewModelFactory(AppDatabase database, String taskId) {
        mDb = database;
        mTaskId = taskId;


    }

    // Uncomment the following method
    @NotNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new StoryViewModel(mDb, mTaskId);
    }


}
