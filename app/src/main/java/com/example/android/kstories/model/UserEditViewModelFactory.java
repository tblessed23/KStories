package com.example.android.kstories.model;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserEditViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    // COMPLETED Add two member variables. One for the database and one for the taskId
    private final AppDatabase mDb;
    private final int mTaskId;


    // Initialize the member variables in the constructor with the parameters received
    public  UserEditViewModelFactory(AppDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;


    }

    // Uncomment the following method
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new UserEditViewModel(mDb, mTaskId);
    }


}
