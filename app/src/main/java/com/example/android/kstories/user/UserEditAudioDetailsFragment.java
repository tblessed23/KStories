package com.example.android.kstories.user;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.UserEditViewModel;
import com.example.android.kstories.model.UserEditViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;


//public class UserEditAudioDetailsFragment extends Fragment {
//
//    // Extra for the task ID to be received in the intent
//    public static final String EXTRA_TASK_ID = "extraTaskId";
//    // Extra for the task ID to be received after rotation
//    public static final String INSTANCE_TASK_ID = "instanceTaskId";
//    // Constants for priority
//    public static final int PRIORITY_HIGH = 1;
//    public static final int PRIORITY_MEDIUM = 2;
//    public static final int PRIORITY_LOW = 3;
//    // Constant for default task id to be used when not in update mode
//    private static final int DEFAULT_TASK_ID = -1;
//    // Constant for logging
//    private static final String TAG = UserEditAudioDetailsActivity.class.getSimpleName();
//    // Fields for views
//    TextInputEditText mEditT, mEditAFN, mEditALN, mEditFN, mEditCity, mEditCounty, mEditState;
//
//
//    RadioGroup mRadioGroup;
//    Button mButton;
//
//    private int mTaskId = DEFAULT_TASK_ID;
//
//    // Member variable for the Database
//    private AppDatabase mDb;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        if (getArguments() != null) {
////
////        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_user_edit_audio_details, container, false);
//
//
//        initViews();
//
//        // Initialize member variable for the data base
//        mDb = AppDatabase.getInstance(getContext());
//
//        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
//            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
//        }
//
//
//        return rootView;
//    }
//
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putInt(INSTANCE_TASK_ID, mTaskId);
//        super.onSaveInstanceState(outState);
//    }
//
//    /**
//     * initViews is called from onCreate to init the member variable views
//     */
//    private void initViews() {
//        mEditAFN = getView().findViewById(R.id.ancestor_first_name);
//        mEditALN = getView().findViewById(R.id.ancestor_last_name);
//        mEditT = getView().findViewById(R.id.story_title);
//        mEditFN = getView().findViewById(R.id.family_name);
//        mEditCity = getView().findViewById(R.id.story_city);
//        mEditCounty = getView().findViewById(R.id.story_county);
//        mEditState = getView().findViewById(R.id.story_state);
//
//
//        mButton = getView().findViewById(R.id.saveButton);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onSaveButtonClicked();
//            }
//        });
//    }
//
//    /**
//     * populateUI would be called to populate the UI when in update mode
//     *
//     * @param stories the taskEntry to populate the UI
//     */
//    private void populateUI(Story stories) {
//        //  return if the task is null
//        if (stories == null) {
//            return;
//        }
//
//        // use the variable task to populate the UI
//        mEditT.setText(stories.getAudiotitle());
//        mEditAFN.setText(stories.getAncestorfirstname());
//        mEditALN.setText(stories.getAncestorlastname());
//        mEditFN.setText(stories.getFamilyname());
//        mEditCity.setText(stories.getStorycity());
//        mEditCounty.setText(stories.getStorycounty());
//        mEditState.setText(stories.getStorystate());
//
//    }
//
//    /**
//     * onSaveButtonClicked is called when the "save" button is clicked.
//     * It retrieves user input and inserts that new task data into the underlying database.
//     */
//    public void onSaveButtonClicked() {
//
//        String audiotitle = String.valueOf(mEditT);
//        String ancestorfirstname = String.valueOf(mEditAFN);
//        String ancestorlastname = String.valueOf(mEditALN);
//        String familyname = String.valueOf(mEditFN);
//        String storycity = String.valueOf(mEditCity);
//        String storycounty = String.valueOf(mEditCounty);
//        String storystate = String.valueOf(mEditState);
//
//
//        //Create a date variable and assign to it the current Date
//        // Date date = new Date();
//
//
//        // Make taskEntry final so it is visible inside the run method
//        final Story taskEntry = new Story(audiotitle, storycity, storycounty, storystate, ancestorfirstname, ancestorlastname, familyname, null, null);
//        // Get the diskIO Executor from the instance of AppExecutors and
//        // call the diskIO execute method with a new Runnable and implement its run method
//        AppExecutors.getInstance().diskIO().execute(new Runnable() {
//            @Override
//            public void run() {
//                // Insert the task only if mTaskId matches DEFAULT_TASK_ID
//                // Otherwise update it
//                // call finish in any case
//                if (mTaskId == DEFAULT_TASK_ID) {
//                    // insert new task
//                    mDb.storyDao().insertTask(taskEntry);
//                } else {
//                    //update task
//                    taskEntry.setUserId(mTaskId);
//                    mDb.storyDao().updateTask(taskEntry);
//                }
//                getActivity().finish();
//            }
//        });
//    }
//}
//
