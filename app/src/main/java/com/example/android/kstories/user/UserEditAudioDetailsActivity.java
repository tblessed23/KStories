package com.example.android.kstories.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Favorites;
import com.example.android.kstories.model.Story;

import com.example.android.kstories.model.UserEditViewModel;
import com.example.android.kstories.model.UserEditViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserEditAudioDetailsActivity extends AppCompatActivity {

    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Constant for logging
    private static final String TAG = UserEditAudioDetailsActivity.class.getSimpleName();
    // Fields for views
    TextInputEditText mEditT, mEditAFN, mEditALN, mEditFN, mEditCity, mEditCounty, mEditState;
    TextView mEditUrl, mDateTime;
    private Story stories;
String audiotitle;
    String titleFavorities;
    int storyId;
    String titleFavs;
    String titlea;
    // Constant for date format
    private static final String DATE_FORMAT = "MM/dd/yyy";
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    Button mButton;
    Button mFavoriteButton;

    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
   private AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        mFavoriteButton = findViewById(R.id.favoriteButton);
        initViews();

        // Initialize member variable for the data base
        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
//        if (getIntent().hasExtra(Extr)){
//            titleFavorities = stories.getAudiotitle();
//        }
            //titleFavorities.
        //titleFavorities.setText(getIntent().getStringExtra("title"));

        // Using getParcelableExtra(String key) method
        if (intent.hasExtra("Stories")) {
            stories= intent.getParcelableExtra("Stories");



            titlea = stories.getAudiotitle();

        }

        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText(R.string.update_button);

            if (mTaskId == DEFAULT_TASK_ID) {

                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);

                // Remove the logging and the call to loadTaskById, this is done in the ViewModel now
                // Declare a AddTaskViewModelFactory using mDb and mTaskId
                UserEditViewModelFactory factory = new UserEditViewModelFactory(mDb, mTaskId);
                // Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
                // for that use the factory created above AddTaskViewModel
                final UserEditViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(UserEditViewModel.class);

                // Observe the LiveData object in the ViewModel. Use it also when removing the observer
                viewModel.getTask().observe(this, new Observer<Story>() {
                    @Override
                    public void onChanged(@Nullable Story taskEntry) {
                        viewModel.getTask().removeObserver(this);
                        populateUI(taskEntry);
                        queryFavorites();
                    }
                });
            }

        }



    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
public void queryFavorites(){
    LiveData<Favorites> currentId = mDb.favoritesDao().loadTaskById(mTaskId);
    currentId.observe(this, new Observer<Favorites>() {
        @Override
        public void onChanged(Favorites favorites) {
            if (favorites !=null && favorites.getId()==mTaskId){
                Drawable image = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_favorite_24, null);
                mFavoriteButton.setEnabled(false);
                mFavoriteButton.setText(R.string.favoritebutton);
                mFavoriteButton.setCompoundDrawablesWithIntrinsicBounds(image, null, null, null);
            }else {
                mFavoriteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        saveButton();
                    }
                });
            }
        }
    });

}

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */

    public void saveButton() {

       titlea = titleFavs;
        int id = mTaskId;

        final Favorites favorites = new Favorites(id, titlea);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                mDb.favoritesDao().insertFavorites(favorites);
                finish();
            }
        });



    }

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        mEditAFN = findViewById(R.id.ancestor_first_name);
        mEditALN= findViewById(R.id.ancestor_last_name);
        mEditT=findViewById(R.id.story_title);
        mEditFN=findViewById(R.id.family_name);
        mEditCity=findViewById(R.id.story_city);
        mEditCounty=findViewById(R.id.story_county);
        mEditState=findViewById(R.id.story_state);
        mEditUrl=findViewById(R.id.url_text_view);
        mDateTime=findViewById(R.id.taskUpdatedAt);
        mButton = findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param stories the taskEntry to populate the UI
     */
    private void populateUI(Story stories) {
        //  return if the task is null
        if (stories == null) {
            return;
        }

        // use the variable task to populate the UI
        mEditT.setText(stories.getAudiotitle());
        mEditAFN.setText(stories.getAncestorfirstname());
        mEditALN.setText(stories.getAncestorlastname());
        mEditFN.setText(stories.getFamilyname());
        mEditCity.setText(stories.getStorycity());
        mEditCounty.setText(stories.getStorycounty());
        mEditState.setText(stories.getStorystate());
        mEditUrl.setText(stories.getAudioUrl());
        mDateTime.setText(dateFormat.format(stories.getUpdatedAt()));


    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {

           audiotitle = mEditT.getText().toString();
           String ancestorfirstname = mEditAFN.getText().toString();
        String ancestorlastname = mEditALN.getText().toString();
        String familyname = mEditFN.getText().toString();
        String storycity = mEditCity.getText().toString();
        String storycounty = mEditCounty.getText().toString();
        String storystate =mEditState.getText().toString();
        String audiourl = mEditUrl.getText().toString();


        //Create a date variable and assign to it the current Date
        Date date = new Date();


        // Make taskEntry final so it is visible inside the run method
        final Story taskEntry = new Story(audiotitle, storycity, storycounty, storystate, ancestorfirstname, ancestorlastname, familyname, audiourl, date);
        // Get the diskIO Executor from the instance of AppExecutors and
        // call the diskIO execute method with a new Runnable and implement its run method
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // COMPLETED (9) insert the task only if mTaskId matches DEFAULT_TASK_ID
                // Otherwise update it
                // call finish in any case
                if (mTaskId == DEFAULT_TASK_ID) {
                    // insert new task
                    mDb.storyDao().insertTask(taskEntry);
                } else {
                    //update task
                    taskEntry.setUserId(mTaskId);
                    mDb.storyDao().updateTask(taskEntry);
                }
                finish();
            }
        });
    }




}