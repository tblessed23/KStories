package com.example.android.kstories.user.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android.kstories.R;
import com.example.android.kstories.user.PlayAudioActivity;
import com.example.android.kstories.user.RecordAudioActivity;


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
                Intent recordAudioIntent = new Intent(getContext(), RecordAudioActivity.class);
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
                Intent editProfileIntent = new Intent(getContext(), PlayAudioActivity.class);
                startActivity(editProfileIntent);


            }
        });


        return rootView;
    }
}