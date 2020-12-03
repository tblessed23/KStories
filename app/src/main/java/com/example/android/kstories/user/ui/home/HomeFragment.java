package com.example.android.kstories.user.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.kstories.R;
import com.example.android.kstories.user.UserPlayAudioActivity;
import com.example.android.kstories.user.UserProfileActivity;
import com.example.android.kstories.user.UserRecordAudioActivity;
import com.example.android.kstories.user.UserSavedAudioActivity;



public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        // Find the Button that will go to the Record Audio page
        Button recordAudio = (Button) rootView.findViewById(R.id.record_audio_button);

        // Set a click listener on that View
        recordAudio.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent recordAudioIntent = new Intent(getContext(), UserRecordAudioActivity.class);
                startActivity(recordAudioIntent);


            }
        });


        // Find the Button that will go to the Record Audio page
        Button edituserProfile = (Button) rootView.findViewById(R.id.edit_user_details_button);

        // Set a click listener on that View
        edituserProfile.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent editProfileIntent = new Intent(getContext(), UserPlayAudioActivity.class);
                startActivity(editProfileIntent);


            }
        });

        // Find the Button that will go to the Record Audio page
        Button edituserAudio = (Button) rootView.findViewById(R.id.audio_edit_user_details_button);

        // Set a click listener on that View
        edituserAudio.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent editAudioIntent = new Intent(getContext(), UserProfileActivity.class);
                startActivity(editAudioIntent);


            }
        });
        return rootView;
    }
}