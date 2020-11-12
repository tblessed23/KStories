package com.example.android.kstories.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Story;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;

public class UserEditAudioDetailsActivity extends AppCompatActivity {

    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";
    // Constants for priority
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_LOW = 3;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Constant for logging
    private static final String TAG = UserEditAudioDetailsActivity.class.getSimpleName();
    // Fields for views
    EditText mEditText;
    TextInputEditText mEditT, mEditAFN, mEditALN, mEditFN, mEditCity, mEditCounty, mEditState;


    RadioGroup mRadioGroup;
    Button mButton;

    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
   private AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);


        initViews();

        // COMPLETED (4) Initialize member variable for the data base
        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText(R.string.update_button);
            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        // COMPLETED (5) Use the loadTaskById method to retrieve the task with id mTaskId and
                        // assign its value to a final TaskEntry variable
                        final Story task = mDb.storyDao().loadStoryById(mTaskId);
                        // COMPLETED (6) Call the populateUI method with the retrieve tasks
                        // Remember to wrap it in a call to runOnUiThread
                        // We will be able to simplify this once we learn more
                        // about Android Architecture Components
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                populateUI(task);
                            }
                        });
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

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        //mEditText = findViewById(R.id.editTextTaskDescription);
        mEditAFN = findViewById(R.id.ancestor_first_name);
        mEditALN= findViewById(R.id.ancestor_last_name);
        mEditT=findViewById(R.id.story_title);
        mEditFN=findViewById(R.id.family_name);
        mEditCity=findViewById(R.id.story_city);
        mEditCounty=findViewById(R.id.story_county);
        mEditState=findViewById(R.id.story_state);



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
        // COMPLETED (7) return if the task is null
        if (stories == null) {
            return;
        }

        // COMPLETED (8) use the variable task to populate the UI
        mEditT.setText(stories.getAudiotitle());
        mEditAFN.setText(stories.getAncestorfirstname());
        mEditALN.setText(stories.getAncestorlastname());
        mEditFN.setText(stories.getFamilyname());
        mEditCity.setText(stories.getStorycity());
        mEditCounty.setText(stories.getStorycounty());
        mEditState.setText(stories.getStorystate());

    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        // COMPLETED (5) Create a description variable and assign to it the value in the edit text
        //String description = mEditText.getText().toString();
        String audiotitle = mEditT.getText().toString();
        String ancestorfirstname = mEditAFN.getText().toString();
        String ancestorlastname = mEditALN.getText().toString();
        String familyname = mEditFN.getText().toString();
        String storycity = mEditCity.getText().toString();
        String storycounty = mEditCounty.getText().toString();
        String storystate = mEditState.getText().toString();


        //Create a date variable and assign to it the current Date
        Date date = new Date();


        // COMPLETED (4) Make taskEntry final so it is visible inside the run method
        final Story taskEntry = new Story(audiotitle, storycity, storycounty, storystate, ancestorfirstname, ancestorlastname, familyname, date);
        // COMPLETED (2) Get the diskIO Executor from the instance of AppExecutors and
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