package com.example.android.kstories.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.User;

import java.util.List;

public class StoryViewModel extends ViewModel {


    //  private UserEditRepository mRepository;
    private LiveData<Story> task;


    public StoryViewModel(){ };
    //Add a task member variable for the TaskEntry object wrapped in a LiveData


    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public StoryViewModel(AppDatabase database, String taskId) {
        task = database.storyDao().loadStoryAgainById(taskId);
    }



    // Create a getter for the task variable
    public LiveData<Story> getTask() {
        return task;
    }




}

