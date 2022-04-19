package com.example.android.kstories.search;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.kstories.NowPlaying;
import com.example.android.kstories.R;
import com.example.android.kstories.databinding.ActivitySearchBinding;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.SearchClickListener {

    private PagedList<Story> searchingactivity;
    SearchView searchView;
    private static final String LOG_TAG = SearchActivity.class.getSimpleName();
    SearchViewModel viewModel;
    private SearchAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setActionBarTitle("Search by City, State");
        ActivitySearchBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        adapter = new SearchAdapter(this, this::onSearchClickListener);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        viewModel.initialFood(AppDatabase.getInstance(this).storyDao());

        mainBinding.setViewModel(viewModel);
        mainBinding.setLifecycleOwner(this);
        getSearchList();


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

//    public void setActionBarTitle(String title) {
//        Objects.requireNonNull(getSupportActionBar()).setTitle(title);}
protected void setActionBarTitle(String title)
{
    ActionBar actionBar = getSupportActionBar();

    if (actionBar != null)
    {
        if (title != null)
        {
            actionBar.setTitle(title);
        } else
        {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }
}

    public void getSearchList() {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
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

    }

    @Override
    public void onSearchClickListener(int itemId) {
        Intent intent = new Intent(SearchActivity.this, NowPlaying.class);
        intent.putExtra(NowPlaying.EXTRA_TASK_ID, itemId);
        startActivity(intent);
    }
}

