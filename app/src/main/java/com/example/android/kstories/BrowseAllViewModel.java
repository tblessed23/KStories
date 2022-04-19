package com.example.android.kstories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.StoryDao;
import com.example.android.kstories.search.DebouncedLiveData;

public class BrowseAllViewModel extends ViewModel {

    public LiveData<PagedList<Story>> listAllStories;
    private LiveData<PagedList<Story>> listAllStoriesInDb;
    public MutableLiveData<String> filterStoryName = new MutableLiveData<>();

    public void initialFood(final StoryDao storyDao) {

        PagedList.Config config = (new PagedList.Config.Builder())
                .setPageSize(10)
                .build();

        listAllStories = Transformations.switchMap(new DebouncedLiveData<>(filterStoryName, 400), input -> {

            if (input == null || input.equals("") || input.equals("%%")) {

                synchronized (this) {
                    //check data is loaded before or not
                    if (listAllStoriesInDb == null)
                        listAllStoriesInDb = new LivePagedListBuilder<>(
                                storyDao.loadAllStoryView(), config)
                                .build();
                }
                return listAllStoriesInDb;
            } else {

                return new LivePagedListBuilder<>(
                        storyDao.loadAllStoriesFromSearch("%" + input + "%"), config)
                        .build();
            }
        });
    }
}




