package com.example.android.kstories.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.firebase.ui.auth.data.model.User;

import java.util.List;

public class UserEditRepository {

    private StoryDao mWordDao;
    private LiveData<List<Story>> mAllWords;

    UserEditRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mWordDao = db.storyDao();

    }

    LiveData<List<Story>> getAllWords() {
        return mAllWords;
    }

    public void insert(Story story) {
        new insertAsyncTask(mWordDao).execute(story);
    }



    // Need to run off main thread
    public void deleteTask(Story story) {
        new deleteWordAsyncTask(mWordDao).execute(story);
    }

    // Static inner classes below here to run database interactions
    // in the background.

    /**
     * Insert a word into the database.
     */
    private static class insertAsyncTask extends AsyncTask<Story, Void, Void> {

        private StoryDao mAsyncTaskDao;

        insertAsyncTask(StoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Story... params) {
            mAsyncTaskDao.insertTask(params[0]);
            return null;
        }
    }



    /**
     *  Delete a single word from the database.
     */
    private static class deleteWordAsyncTask extends AsyncTask<Story, Void, Void> {
        private StoryDao mAsyncTaskDao;

        deleteWordAsyncTask(StoryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Story... params) {
            mAsyncTaskDao.deleteTask(params[0]);
            return null;
        }
    }
}



