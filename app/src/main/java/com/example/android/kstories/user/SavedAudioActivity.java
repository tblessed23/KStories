package com.example.android.kstories.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.kstories.R;

public class SavedAudioActivity extends AppCompatActivity   {
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_user_audio);



        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SavedAudioFragment())
                .commit();

    }


}