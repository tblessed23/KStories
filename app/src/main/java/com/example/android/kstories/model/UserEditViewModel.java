package com.example.android.kstories.model;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.firebase.ui.auth.data.model.User;

import java.util.List;

public class UserEditViewModel extends ViewModel {

  //  private UserEditRepository mRepository;
    private LiveData<Story> task;


    public UserEditViewModel(){ };
    //Add a task member variable for the TaskEntry object wrapped in a LiveData


    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public UserEditViewModel(AppDatabase database, int taskId) {
        task = database.storyDao().loadStoryById(taskId);
    }



    // Create a getter for the task variable
    public LiveData<Story> getTask() {
        return task;
    }

   ;


}
