package com.example.android.kstories.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;
import com.example.android.kstories.databinding.ActivitySearchBinding;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;

public class SearchActivity extends AppCompatActivity implements SearchClickListener {

    public static final String SELECTED_FOOODIE = "foodie";
    private PagedList<Story> searchingactivity;
    SearchView searchView;
    private RecyclerView recyclerView;

    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    SearchViewModel viewModel;
    private SearchAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Get the intent, verify the action and get the query




        ActivitySearchBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        adapter = new SearchAdapter(this, this::onSearchClicked);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        viewModel.initialFood(AppDatabase.getInstance(this).storyDao());

        mainBinding.setViewModel(viewModel);
        mainBinding.setLifecycleOwner(this);
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getSearchList(query);
        }



        RecyclerView recyclerView = mainBinding.recycler;
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);


        //first time set an empty value to get all data
        viewModel.filterStoryName.setValue("");




    }


    public void getSearchList(String query) {

        viewModel.listAllStories.observe(this, searchPaging -> {
            Log.d(LOG_TAG, "lIST is : " + searchPaging);
            try {
                Log.d(LOG_TAG, "list of all page number " + searchPaging.size());
                Log.d(LOG_TAG, "list of food are " + searchPaging);

                searchingactivity = searchPaging;

                adapter.submitList(searchPaging);

            } catch (Exception e) {
            }
        });


    }


    @Override
    public void onSearchClicked(Story fable, View sharedView) {
        Toast.makeText(getApplicationContext(), "The food is: " + fable.getStorystate(),
                Toast.LENGTH_LONG).show();

//        //CONTENT TRANSITION
//    /*    Bundle bundle = ActivityOptions
//                .makeSceneTransitionAnimation(this)
//                .toBundle();
//           */
//
//        //sHARED transition
//        Bundle bundle = ActivityOptions
//                .makeSceneTransitionAnimation(
//                        this,
//                        sharedView,
//                        sharedView.getTransitionName())
//                .toBundle();
//
//        Intent intent = new Intent(this, SearchDetailActivity.class);
//        intent.putExtra(SELECTED_FOOODIE, fable);
//        //  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent, bundle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_items);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //just set the current value to search.
                viewModel.filterStoryName.
                        setValue("%" + newText + "%");
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
        // super.onBackPressed();
    }
}
