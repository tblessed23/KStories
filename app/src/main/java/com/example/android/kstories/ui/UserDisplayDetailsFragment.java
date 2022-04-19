package com.example.android.kstories.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;
import com.example.android.kstories.loggingin.LoggedInViewModel;
import com.example.android.kstories.loggingin.UserViewModel;
import com.example.android.kstories.loggingin.UserViewModelFactory;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.MainViewModel;
import com.example.android.kstories.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class UserDisplayDetailsFragment extends Fragment {



    //Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();
    private LoggedInViewModel loggedInViewModel;
    Button mButton;
    private TextView loggedInUserTextView;
    // Member variables for the adapter and RecyclerView
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private List<User> mStoryEntries = new ArrayList<>();
    private Button logOutButton;

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    //Implement Database
    private AppDatabase mDb;

    public UserDisplayDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//
//        }

//            loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
//            loggedInViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
//                @Override
//                public void onChanged(FirebaseUser firebaseUser) {
//                    if (firebaseUser != null) {
//                        loggedInUserTextView.setText("Logged In User: " + firebaseUser.getEmail());
//                        logOutButton.setEnabled(true);
//                    } else {
//                        logOutButton.setEnabled(false);
//                    }
//                }
//            });
//
//            loggedInViewModel.getLoggedOutLiveData().observe(this, new Observer<Boolean>() {
//                @Override
//                public void onChanged(Boolean loggedOut) {
//                    if (loggedOut) {
//                        Toast.makeText(getContext(), "User Logged Out", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getActivity(), MainActivity.class);
//                        startActivity(intent);
//                    }
//                }
//            });

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_user_display_details, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

        // Set the RecyclerView to its corresponding view
        // mRecyclerView = rootView.findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new UserAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);


        //Initialize Database
        mDb = AppDatabase.getInstance(getContext());

        setupViewModel();


//        if (mStoryEntries.isEmpty()) {
//            mRecyclerView.setVisibility(View.GONE);
//            mEmptyStateTextView.setVisibility(View.VISIBLE);
//        }
//        else {
//            mRecyclerView.setVisibility(View.VISIBLE);
//            mEmptyStateTextView.setVisibility(View.GONE);
//        }


//            loggedInUserTextView = rootView.findViewById(R.id.fragment_loggedin_loggedInUser);
//            logOutButton = rootView.findViewById(R.id.fragment_loggedin_logOut);
//
//            logOutButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    loggedInViewModel.logOut();
//                }
//            });


        return rootView;
    }


//         private void   setupViewModel() {
//                MainViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
//                viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
//                    @Override
//                    public void onChanged(@Nullable List<User> taskEntries) {
//                        Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
//                        mAdapter.setTasks(taskEntries);
//                    }
//                });
//            }

//    private void setupViewModel() {
//        MainViewModel viewModel=new ViewModelProvider(this).get(MainViewModel.class);
//        viewModel.getTasks().observe(getActivity(), new Observer<List<User>>() {
//            @Override
//            public void onChanged(@Nullable List<User> taskEntries) {
//                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
//                mAdapter.setTasks(taskEntries);
//            }
//        });
//    }

    private void setupViewModel(){

        String mTaskId = FirebaseAuth.getInstance().getUid();
        // Remove the logging and the call to loadTaskById, this is done in the ViewModel now
        // Declare a AddTaskViewModelFactory using mDb and mTaskId
        UserViewModelFactory factory = new UserViewModelFactory(mDb, mTaskId);
        // Declare a AddTaskViewModel variable and initialize it by calling ViewModelProviders.of
        // for that use the factory created above AddTaskViewModel
        final UserViewModel viewModel
                = ViewModelProviders.of(this, factory).get(UserViewModel.class);

        // Observe the LiveData object in the ViewModel. Use it also when removing the observer
        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> taskEntry) {
                viewModel.getTasks().removeObserver(this);
                mAdapter.setTasks(taskEntry);

            }
        });

    }

//    private void setupViewModel() {
//        MainViewModel viewModel=new ViewModelProvider(this).get(MainViewModel.class);
//        viewModel.getTasks().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
//            @Override
//            public void onChanged(@Nullable List<User> taskEntries) {
//                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
//                mAdapter.setTasks(taskEntries);
//            }
//        });
//    }

}
