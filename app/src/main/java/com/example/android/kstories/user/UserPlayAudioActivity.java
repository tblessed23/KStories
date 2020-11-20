package com.example.android.kstories.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.android.kstories.R;
import com.example.android.kstories.model.Story;

public class UserPlayAudioActivity extends AppCompatActivity {

    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    private Story stories;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_play_audio);


        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            stories = intent.getParcelableExtra(EXTRA_TASK_ID);
            position = intent.getIntExtra(getResources().getString(R.string.intent_key_steps_position), 0);

        }

        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_TASK_ID, (Parcelable) stories);
        bundle.putInt(getResources().getString(R.string.intent_key_steps_position), position);
    // set MyFragment Arguments
        UserPlayAudioFragment myObj = new UserPlayAudioFragment();
        myObj.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new UserPlayAudioFragment())
                .commit();
    }
}