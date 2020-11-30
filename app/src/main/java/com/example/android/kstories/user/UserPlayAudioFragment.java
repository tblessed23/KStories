package com.example.android.kstories.user;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.UserEditViewModel;
import com.example.android.kstories.model.UserEditViewModelFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;



//public class UserPlayAudioFragment extends Fragment {
//
//    public static final String EXTRA_TASK_ID = "extraTaskId";
//    // Extra for the task ID to be received after rotation
//    public static final String INSTANCE_TASK_ID = "instanceTaskId";
//
//    // Constant for default task id to be used when not in update mode
//    private static final int DEFAULT_TASK_ID = -1;
//    // Constant for logging
//    private static final String TAG = UserEditAudioDetailsActivity.class.getSimpleName();
//    // Fields for views
//    TextView mEditT, mEditAfN;
//
//    //Exo-player Variables
//
//    private PlayerView mPlayerView;
//    private SimpleExoPlayer player;
//    private boolean playWhenReady = true;
//    private int currentWindow = 0;
//    private long playbackPosition = 0;
//    private Uri videoLink;
//    String textvurl;
//    private Story stories;
//int position;
//    private int mTaskId = DEFAULT_TASK_ID;
//
//
//    public UserPlayAudioFragment() {
//        // Required empty public constructor
//    }
//
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//
//            stories = getArguments().getParcelable(EXTRA_TASK_ID);
//            position = getArguments().getInt(getResources().getString(R.string.intent_key_steps_position));
//        }
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_user_play_audio, container, false);
//
//        initViews();
//
//
//        // Initialize member variable for the data base
//        // Member variable for the Database
//        AppDatabase mDb = AppDatabase.getInstance(getActivity());
//
//        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
//            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
//        }
//
//
//                // Remove the logging and the call to loadTaskById, this is done in the ViewModel now
//                // Declare a AddTaskViewModelFactory using mDb and mTaskId
//                UserEditViewModelFactory factory = new UserEditViewModelFactory(mDb, mTaskId);
//// Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
//// for that use the factory created above AddTaskViewModel
//                final UserEditViewModel viewModel
//                        = ViewModelProviders.of(this, factory).get(UserEditViewModel.class);
//
//                // Observe the LiveData object in the ViewModel. Use it also when removing the observer
//                viewModel.getTask().observe(getActivity(), new Observer<Story>() {
//                    @Override
//                    public void onChanged(@Nullable Story taskEntry) {
//                        viewModel.getTask().removeObserver(this);
//                        populateUI(taskEntry);
//
//                    }
//
//                });
//
//
//            }
//        } return rootView;
//    }
//
//    @Override
//  public void onSaveInstanceState(Bundle outState) {
//        outState.putInt(INSTANCE_TASK_ID, mTaskId);
//        super.onSaveInstanceState(outState);
//    }
//
//    /**
//     * initViews is called from onCreate to init the member variable views
//     */
//    private void initViews() {
//
//        mEditT = getView().findViewById(R.id.story_title);
//        //mEditAfN = findViewById(R.id.ancestor_first_name);
//
//        // Initialize the player view.
//        mPlayerView = getView().findViewById(R.id.playerView);
//
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
//
//        mEditT.setText(stories.getAudiotitle());
//        //mEditAfN.setText(stories.getAudioUrl());
//
//        assert videoLink != null;
//        videoLink = Uri.parse(stories.getAudioUrl());
//
//
//        initializePlayer();
//
//
//
//    }
//
//
//    private void initializePlayer() {
//        if (player == null) {
//
//            player = new SimpleExoPlayer.Builder(getActivity()).build();
//            mPlayerView.setPlayer(player);
//            MediaItem mediaItem = MediaItem.fromUri(videoLink);
//            player.setMediaItem(mediaItem);
//            player.setPlayWhenReady(playWhenReady);
//            player.seekTo(currentWindow, playbackPosition);
//            player.prepare();
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        hideSystemUi();
//    }
//
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        mPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (Util.SDK_INT < 16) {
//            releasePlayer();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (Util.SDK_INT >= 16) {
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
//
//}