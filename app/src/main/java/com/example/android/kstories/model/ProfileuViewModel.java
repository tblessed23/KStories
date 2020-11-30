package com.example.android.kstories.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ProfileuViewModel extends ViewModel {


    private LiveData<Profileu> task;


    public ProfileuViewModel(){ };
    //Add a task member variable for the TaskEntry object wrapped in a LiveData


    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public ProfileuViewModel(AppDatabase database, int taskId) {
        task = database.profileuDao().loadProfileById(taskId);
    }



    // Create a getter for the task variable
    public LiveData<Profileu> getTask() {
        return task;
    }

    ;

}
