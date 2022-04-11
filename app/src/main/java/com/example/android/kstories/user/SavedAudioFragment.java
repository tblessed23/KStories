package com.example.android.kstories.user;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.kstories.StoriesRecyclerView;
import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.MainViewModel;
import com.example.android.kstories.model.SavedAudioViewModel;
import com.example.android.kstories.model.SavedAudioViewModelFactory;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SavedAudioFragment extends Fragment  {

    //Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    // Member variables for the adapter and RecyclerView
    private StoriesRecyclerView mRecyclerView;
    private StoryAdapter mAdapter;
    private List<Story> mStoryEntries = new ArrayList<>();
    Button mButton;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    //Implement Database
    private AppDatabase mDb;

    public SavedAudioFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//
//        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.activity_saved_user_audio, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);
        mRecyclerView.setEmptyView(rootView.findViewById(R.id.empty_view));

        // Set the RecyclerView to its corresponding view
       // mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new StoryAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);


        //Initialize Database
        mDb = AppDatabase.getInstance(getContext());

        setupViewModel();

       return rootView;
    }



    private void setupViewModel(){


        String mTaskId = FirebaseAuth.getInstance().getUid();
        // Remove the logging and the call to loadTaskById, this is done in the ViewModel now
        // Declare a AddTaskViewModelFactory using mDb and mTaskId
        SavedAudioViewModelFactory factory = new SavedAudioViewModelFactory(mDb, mTaskId);
        // Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
        // for that use the factory created above AddTaskViewModel
        final SavedAudioViewModel viewModel
                = ViewModelProviders.of(this, factory).get(SavedAudioViewModel.class);

        // Observe the LiveData object in the ViewModel. Use it also when removing the observer
        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<Story>>() {
            @Override
            public void onChanged(@Nullable List<Story> taskEntry) {
                viewModel.getTasks().removeObserver(this);
                mAdapter.setTasks(taskEntry);

            }
        });

    }

}