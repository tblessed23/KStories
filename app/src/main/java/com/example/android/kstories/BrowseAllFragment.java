package com.example.android.kstories;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.MainViewModel;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.user.UserStoryAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class BrowseAllFragment extends Fragment {

    //Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();

    // Member variables for the adapter and RecyclerView
    private RecyclerView mRecyclerView;
    private BrowseAllAdapter mAdapter;


    //Implement Database
    private AppDatabase mDb;

    public BrowseAllFragment() {
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
        View rootView = inflater.inflate(R.layout.activity_browse_all, container, false);

        // Set the RecyclerView to its corresponding view
        mRecyclerView = rootView.findViewById(R.id.recyclerViewBrowse);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new BrowseAllAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);


        //Initialize Database
        mDb = AppDatabase.getInstance(getContext());

        setupViewModel();

        return rootView;
    }

    private void setupViewModel() {

        //MainViewModel viewModel2 = ViewModelProvider(this).get(MainViewModel.class);
        MainViewModel viewModel=new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<Story>>() {
            @Override
            public void onChanged(@Nullable List<Story> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                mAdapter.setTasks(taskEntries);
            }
        });
    }


}