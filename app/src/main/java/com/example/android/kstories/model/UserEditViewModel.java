package com.example.android.kstories.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.auth.data.model.User;

public class UserEditViewModel extends ViewModel {
    public UserEditViewModel(){ };
    // COMPLETED (6) Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<Story> task;

    // COMPLETED (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public UserEditViewModel(AppDatabase database, int taskId) {
        task = database.storyDao().loadStoryById(taskId);
    }

    // COMPLETED (7) Create a getter for the task variable
    public LiveData<Story> getTask() {
        return task;
    }

   ;

}
