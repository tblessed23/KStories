package com.example.android.kstories.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android.kstories.BrowseAllFragment;
import com.example.android.kstories.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SearchFragment())
                .commit();
    }
}