package com.example.android.kstories.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.android.kstories.R;

public class UserWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


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

    /**
     * Methods for setting up the menu
     **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our visualizer_menu layout to this menu */
        inflater.inflate(R.menu.nav_items, menu);
        /* Return true so that the visualizer_menu is displayed in the Toolbar */
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, FavoritesActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}