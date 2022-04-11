package com.example.android.kstories;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.user.StoryViewModel;
import com.example.android.kstories.user.StoryViewModelFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.auth.FirebaseAuth;


/***This activity ties into the BrowseAllActivity/BrowseAllAdapter/BrowseAllFragment**/

public class NowPlaying extends AppCompatActivity {

    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    // Constant for default task id to be used when not in update mode
    private static final String DEFAULT_TASK_ID = FirebaseAuth.getInstance().getUid();

    private String mTaskId = DEFAULT_TASK_ID;
    // Constant for logging
    private static final String TAG = NowPlaying.class.getSimpleName();
    // Fields for views
    TextView mEditT, mEditAfN, mState, mCounty, mFullname, mFamilyName;

    //Exo-player Variables

    private PlayerView mPlayerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private Uri videoLink;
    String textvurl;
    private Story storiesa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);

        initViews();


        // Initialize member variable for the data base
        // Member variable for the Database
        AppDatabase mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getString(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {

            //  mButton.setText(R.string.update_button);
            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getStringExtra(DEFAULT_TASK_ID);
                // Remove the logging and the call to loadTaskById, this is done in the ViewModel now
                // Declare a AddTaskViewModelFactory using mDb and mTaskId
                StoryViewModelFactory factory = new StoryViewModelFactory(mDb, mTaskId);
// Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
// for that use the factory created above AddTaskViewModel
                final StoryViewModel viewModel
                        = ViewModelProviders.of(this, factory).get(StoryViewModel.class);

                // Observe the LiveData object in the ViewModel. Use it also when removing the observer
                viewModel.getTask().observe(this, new Observer<Story>() {
                    @Override
                    public void onChanged(@Nullable Story taskEntry) {
                        viewModel.getTask().removeObserver(this);
                        populateUI(taskEntry);

                    }

                });


            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {

        mEditT = findViewById(R.id.titleDetails);
        mState = findViewById(R.id.stateDetails);
        mCounty = findViewById(R.id.countyDetails);
        mFullname = findViewById(R.id.nameDetails);
        mFamilyName = findViewById(R.id.familynameDetails);

        // Initialize the player view.
        mPlayerView = findViewById(R.id.playerView);

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

        String placeholderState = stories.getStorycity() + "," + "" +  stories.getStorystate();
        String placeholderFullname = stories.getAncestorlastname()+ "," + "" +  stories.getAncestorfirstname();

        mEditT.setText(stories.getAudiotitle());
        mState.setText(placeholderState);
        mCounty.setText(stories.getStorycounty());
        mFullname.setText(placeholderFullname);
        mFamilyName.setText(stories.getFamilyname());
        //mEditAfN.setText(stories.getAudioUrl());

        assert videoLink != null;
        videoLink = Uri.parse(stories.getAudioUrl());


        initializePlayer();



    }


    private void initializePlayer() {
        if (player == null) {

            player = new SimpleExoPlayer.Builder(this).build();
            mPlayerView.setPlayer(player);
            MediaItem mediaItem = MediaItem.fromUri(videoLink);
            player.setMediaItem(mediaItem);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
            player.prepare();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 16) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 16) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

}