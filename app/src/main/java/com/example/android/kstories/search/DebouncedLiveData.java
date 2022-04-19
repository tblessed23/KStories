package com.example.android.kstories.search;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class DebouncedLiveData<T> extends MediatorLiveData<T> {

    private final LiveData<T> mSource;
    private final int mDuration;
    private final Runnable debounceRunnable = new Runnable() {
        @Override
        public void run() {
            DebouncedLiveData.this.postValue(mSource.getValue());
        }
    };
    private final Handler handler = new Handler();

    public DebouncedLiveData(LiveData<T> source, int duration) {
        this.mSource = source;
        this.mDuration = duration;

        this.addSource(mSource, new Observer<T>() {
            @Override
            public void onChanged(T t) {
                handler.removeCallbacks(debounceRunnable);
                handler.postDelayed(debounceRunnable, mDuration);
            }
        });
    }
}


