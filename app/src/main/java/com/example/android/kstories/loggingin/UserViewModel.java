package com.example.android.kstories.loggingin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.MainViewModel;
import com.example.android.kstories.model.User;

import java.util.List;

public class UserViewModel extends ViewModel {

    //Constant for logging
    private static final String TAG = UserViewModel.class.getSimpleName();

    private final LiveData<List<User>> tasks;

    // Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public UserViewModel(AppDatabase database, String taskId) {
        tasks = database.userDao().loadTaskById(taskId);
    }


    public LiveData<List<User>> getTasks() {
        return tasks;
    }
}
