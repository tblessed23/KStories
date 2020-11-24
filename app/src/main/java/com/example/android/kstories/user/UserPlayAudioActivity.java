package com.example.android.kstories.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.UserEditViewModel;
import com.example.android.kstories.model.UserEditViewModelFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import dm.audiostreamer.AudioStreamingManager;

public class UserPlayAudioActivity extends AppCompatActivity {

    private AudioStreamingManager streamingManager;
    private PlayerView mPlayerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    public static final String EXTRA_TASK_ID = "extraTaskId";
    private Story stories;
    private String audiourl;
    private Uri videoLink;
    private int position;
    // Member variable for the Database
    private AppDatabase mDb;
    private Context context;
    TextView audiolink;
    String textviewURL;
    private List<Story> mStoryEntries;

    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;

    private int mTaskId = DEFAULT_TASK_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_play_audio);
        //populateUI(stories);
        //Get Current Intent



        Intent intent = getIntent();

        if (intent == null) {
            closeOnError();
        }


//        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
//
//            if (mTaskId == DEFAULT_TASK_ID) {
//
//                textviewURL = stories.getAudiotitle();


        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {

            if (mTaskId == DEFAULT_TASK_ID) {
                // populate the UI
                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
                populateUI(stories);
//        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
//            int position = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
//            if (position == DEFAULT_TASK_ID) {

               // textviewURL = mStoryEntries.get(position).getAudiotitle();
                //Log.i("Playvideo", "File uri: " + textviewURL);




//
                // EXTRA_POSITION not found in intent
                // closeOnError();
            // return;
            }
        }


//        if (intent != null && intent.hasExtra("Stories")) {
//            stories = intent.getParcelableExtra("Stories");
//        }


//            if (mTaskId == DEFAULT_TASK_ID) {
//
//
//                mTaskId = intent.getIntExtra(EXTRA_TASK_ID, DEFAULT_TASK_ID);
//
//
//                // COMPLETED (9) Remove the logging and the call to loadTaskById, this is done in the ViewModel now
//                // COMPLETED (10) Declare a AddTaskViewModelFactory using mDb and mTaskId
//                UserEditViewModelFactory factory = new UserEditViewModelFactory(mDb, mTaskId);
//                // COMPLETED (11) Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
//                // for that use the factory created above AddTaskViewModel
//                final UserEditViewModel viewModel
//                        = new ViewModelProvider(this).get(UserEditViewModel.class);
//
//                // COMPLETED (12) Observe the LiveData object in the ViewModel. Use it also when removing the observer
//                viewModel.getTask().observe(this, new Observer<Story>() {
//                    @Override
//                    public void onChanged(@Nullable Story taskEntry) {
//                        viewModel.getTask().removeObserver(this);
//                        assert taskEntry != null;
//                        populateUI(taskEntry);
//                    }
//                });
//
//            }



//
        //mPlayerView = findViewById(R.id.video_view);
//
//
//        Log.i("Playvideo", "File uri: " + videoLink);
//
//
//        videoLink = Uri.parse(audiourl);
//        initializePlayer();
//        // Using getParcelableExtra(String key) method
//        assert intent != null;
//        if (intent.hasExtra(getResources().getString(R.string.intent_key_stories))) {
//            stories = intent.getParcelableExtra(getResources().getString(R.string.intent_key_stories));
//            position = intent.getIntExtra(getResources().getString(R.string.intent_key_steps_position), 0);
//
//        }
//
//
////        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
////            stories = intent.getParcelableExtra(EXTRA_TASK_ID);
////            position = intent.getIntExtra(getResources().getString(R.string.intent_key_steps_position), 0);
////
////        }
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(getResources().getString(R.string.intent_key_stories), (Parcelable) stories);
//        bundle.putInt(getResources().getString(R.string.intent_key_steps_position), position);
//    // set MyFragment Arguments
//        UserPlayAudioFragment myObj = new UserPlayAudioFragment();
//        myObj.setArguments(bundle);
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, new UserPlayAudioFragment())
//                .commit();
    }


    private void populateUI(Story stories) {
        //  return if the task is null
        if (stories == null) {
            return;
        }

        audiolink = findViewById(R.id.video_link);
        audiolink.setText(stories.getAudiotitle());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

//    /**
//     * initViews is called from onCreate to init the member variable views
//     */
//    private void initViews() {
//     initializePlayer();
//    }

//    private void initializePlayer() {
//        player = new SimpleExoPlayer.Builder(this).build();
//        mPlayerView.setPlayer(player);
//        MediaItem mediaItem = MediaItem.fromUri(videoLink);
//        player.setMediaItem(mediaItem);
//        player.setPlayWhenReady(playWhenReady);
//        player.seekTo(currentWindow, playbackPosition);
//        player.prepare();
//
//
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (Util.SDK_INT >= 24) {
//            initializePlayer();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        hideSystemUi();
//        if ((Util.SDK_INT < 24 || player == null)) {
//            initializePlayer();
//        }
//    }
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (Util.SDK_INT < 24) {
//            releasePlayer();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (Util.SDK_INT >= 24) {
//            releasePlayer();
//        }
//    }
//
//    private void releasePlayer() {
//        if (player != null) {
//            playWhenReady = player.getPlayWhenReady();
//            playbackPosition = player.getCurrentPosition();
//            currentWindow = player.getCurrentWindowIndex();
//            player.release();
//            player = null;
//        }
//    }

}