package com.example.android.kstories.search;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kstories.R;
import com.example.android.kstories.model.Story;

public class SearchAdapter extends PagedListAdapter<Story, SearchAdapter.SearchViewHolder> {
    private static final String LOG_TAG = SearchAdapter.class.getSimpleName();
    private Activity activity;
    private SearchClickListener mSearchClickListener;



    public SearchAdapter( Activity activity, SearchClickListener listener) {
        super(Story.DIFF_CALLBACK);
        this.activity = activity;
        this.mSearchClickListener = listener;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.search_list,
                parent,
                false   );
        return new SearchViewHolder(view, mSearchClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {

        if (position <= -1) {  return; }

        Story stories = getItem(position);
        final String name = stories.getStorystate();
        final String restaurant = stories.getStorycity();




        try {holder.txtState.setText(name);
            holder.txtCity.setText(restaurant);

            Log.d(LOG_TAG, "The value of food is: " + stories);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSearchClickListener.onSearchClicked(stories, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();  }
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        private TextView txtState;
        private TextView txtCity;

        SearchClickListener mSearchClicked;

        public SearchViewHolder(@NonNull View itemView, SearchClickListener listener) {
            super(itemView);
            mSearchClicked= listener;
            txtState = itemView.findViewById(R.id.searchstorystatetext_view);
            txtCity= itemView.findViewById(R.id.searchstorycitytext_view);



        }



    }
}

