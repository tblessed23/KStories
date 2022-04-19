package com.example.android.kstories.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class SavedAudioViewModel extends ViewModel {

    //Constant for logging
    private static final String TAG =SavedAudioViewModel.class.getSimpleName();

    private final LiveData<List<Story>> tasks;

    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public SavedAudioViewModel(AppDatabase database, String taskId) {
        tasks = database.storyDao().loadStoryById(taskId);
    }




    public LiveData<List<Story>> getTasks() {
        return tasks;
    }
}
