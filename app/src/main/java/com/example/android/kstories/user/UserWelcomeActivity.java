package com.example.android.kstories.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.kstories.MusicActivity;
import com.example.android.kstories.R;

public class UserWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);


        // Find the Button that will go to the Record Audio page
        Button recordAudio = (Button) findViewById(R.id.record_audio_button);

        // Set a click listener on that View
        recordAudio.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent recordAudioIntent = new Intent(UserWelcomeActivity.this, UserRecordAudioActivity.class);
                startActivity(recordAudioIntent);


            }
        });

        // Find the Button that will go to the Record Audio page
        Button savedAudio = (Button) findViewById(R.id.saved_audio_button);

        // Set a click listener on that View
        savedAudio.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent recordAudioIntent = new Intent(UserWelcomeActivity.this, UserSavedAudioActivity.class);
                startActivity(recordAudioIntent);


            }
        });

        // Find the Button that will go to the Record Audio page
        Button edituserProfile = (Button) findViewById(R.id.edit_user_details_button);

        // Set a click listener on that View
        edituserProfile.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent editProfileIntent = new Intent(UserWelcomeActivity.this, UserPlayAudioActivity.class);
                startActivity(editProfileIntent);


            }
        });

        // Find the Button that will go to the Record Audio page
        Button edituserAudio = (Button) findViewById(R.id.audio_edit_user_details_button);

        // Set a click listener on that View
        edituserAudio.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent editAudioIntent = new Intent(UserWelcomeActivity.this, UserEditAudioDetailsActivity.class);
                startActivity(editAudioIntent);


            }
        });
    }
}