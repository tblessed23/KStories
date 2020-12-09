package com.example.android.kstories.user.ui.aboutus;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.kstories.R;

import java.security.acl.Group;


public class AboutUsFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


          View rootView = inflater.inflate(R.layout.activity_about_us, container, false);

        return rootView;
    }
}