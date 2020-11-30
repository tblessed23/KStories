package com.example.android.kstories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.kstories.user.FavoritesFragment;

public class BrowseAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_all);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new BrowseAllFragment())
                .commit();
    }


}