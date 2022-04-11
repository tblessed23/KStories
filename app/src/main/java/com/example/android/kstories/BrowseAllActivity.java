package com.example.android.kstories;

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import com.example.android.kstories.model.AppDatabase;
//import com.example.android.kstories.model.Story;
//import com.example.android.kstories.user.FavoritesFragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.SearchView;
//import androidx.databinding.DataBindingUtil;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.paging.PagedList;
//import androidx.recyclerview.widget.DefaultItemAnimator;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.msbs.android.asik.databinding.ActivitySearchBinding;
//
//
//import java.util.Objects;
//
//public class BrowseAllActivity extends AppCompatActivity implements BrowseAllAdapter.BrowseAllClickListener {
//
//
//    private PagedList<Story> searchingactivity;
//    SearchView searchView;
//
//
//
//   BrowseAllViewModel viewModel;
//    private BrowseAllAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        setContentView(R.layout.activity_browse_all);
//        ActivitySearchBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_browse_all);
//        adapter = new BrowseAllAdapter(this, this::onBrowseAllClickListener);
//        viewModel = new ViewModelProvider(this).get(BrowseAllViewModel.class);
//
//        viewModel.initialFood(AppDatabase.getInstance(this).storyDao());
//
//        mainBinding.setViewModel(viewModel);
//        mainBinding.setLifecycleOwner(this);
//        getSearchList();
//
//
//        RecyclerView recyclerView = mainBinding.recycler;
//
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//
//        //first time set an empty value to get all data
//        viewModel.filterStoryName.setValue("");
//
//        setActionBarTitle("Search by City, State");
//
//
//    }
//
//    public void setActionBarTitle(String title) {
//        Objects.requireNonNull(getSupportActionBar()).setTitle(title);}
//
//
//    public void getSearchList() {
//
//        viewModel.listAllStories.observe(this, searchPaging -> {
//
//            try {
//
//                searchingactivity = searchPaging;
//
//                adapter.submitList(searchPaging);
//
//            } catch (Exception e) {
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.search);
//        searchView = (SearchView) searchItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //just set the current value to search.
//                viewModel.filterStoryName.
//                        setValue("%" + newText + "%");
//                return false;
//            }
//        });
//        return true;
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        if (!searchView.isIconified()) {
//            searchView.onActionViewCollapsed();
//        } else {
//            super.onBackPressed();
//        }
//
//    }
//
//    @Override
//    public void onBrowseAllClickListener(int itemId) {
//        Intent intent = new Intent(BrowseAllActivity.this, NowPlaying.class);
//        intent.putExtra(NowPlaying.EXTRA_TASK_ID, itemId);
//        startActivity(intent);
//    }
//}
//
