package com.example.android.kstories.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.kstories.R;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FavoritesFragment())
                .commit();

    }
}