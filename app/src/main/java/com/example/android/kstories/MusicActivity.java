package com.example.android.kstories;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

import dm.audiostreamer.AudioStreamingManager;
import dm.audiostreamer.CurrentSessionCallback;
import dm.audiostreamer.MediaMetaData;
import dm.audiostreamer.StreamingManager;

public class MusicActivity extends AppCompatActivity {



//private AudioStreamingManager streamingManager;
//private PlayerView playerView;
//private SimpleExoPlayer player;
//private boolean playWhenReady = true;
//private int currentWindow = 0;
//private long playbackPosition = 0;
//
//
//    private Context context;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_music);
//
//         playerView = findViewById(R.id.video_view);
//
////        this.context = MusicActivity.this;
////        streamingManager = AudioStreamingManager.getInstance(context);
//    }
//
//    private void initializePlayer() {
//        player = new SimpleExoPlayer.Builder(this).build();
//        playerView.setPlayer(player);
//        MediaItem mediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3));
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
//        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
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
































