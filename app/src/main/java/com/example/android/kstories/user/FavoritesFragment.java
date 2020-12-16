package com.example.android.kstories.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.kstories.StoriesRecyclerView;
import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Favorites;
import com.example.android.kstories.model.FavoritesViewModel;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {

    // Constant for logging
    private static final String TAG = FavoritesFragment.class.getSimpleName();
    private AppDatabase mDb;
    private FavoritesAdapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;
private StoriesRecyclerView mRecyclerView;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDb = AppDatabase.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_favorites, container, false);

        setHasOptionsMenu(true);
        mRecyclerView = rootView.findViewById(R.id.recyclerViewFavorites);
        mRecyclerView.setEmptyView(rootView.findViewById(R.id.empty_view_favorites));
        // use a grid layout manager

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // Create a new adapter that takes an empty list of moviess as input
        mAdapter = new FavoritesAdapter(getActivity(), new ArrayList<Favorites>());
        mRecyclerView.setAdapter(mAdapter);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        mRecyclerView.setAdapter(mAdapter);


  /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        //The Adapter holds the items you want to delete
                        int position = viewHolder.getAdapterPosition();
                        List<Favorites> removeFavorite = mAdapter.getTasks();
                        mDb.favoritesDao().deleteFavorites(removeFavorite.get(position));
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);

        setUpViewModel();
        return rootView;
    }


    private void setUpViewModel() {
        //Insert Update andDelete do not have to observe changes in the database. This is for retrieving tass.
        //LiveData is for retrieving data, AppExcutors for the other three
        // Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        FavoritesViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(FavoritesViewModel.class);
        viewModel.getTasks().observe((LifecycleOwner) getActivity(), (Observer<? super List<Favorites>>) new Observer<List<Favorites>>() {
            @Override
            public void onChanged(List<Favorites> favoritesEntries) {
                Log.d(TAG, getResources().getString(R.string.error_log));
                mAdapter.setTasks(favoritesEntries);
            }
        });
    }
}