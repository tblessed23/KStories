package com.example.android.kstories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

//public class BrowseAllAdapter extends PagedListAdapter<Story, BrowseAllAdapter.BrowseViewHolder> {
//    private static final String LOG_TAG = BrowseAllAdapter.class.getSimpleName();
//    private final BrowseAllClickListener mSearchClickListener;
//
//
//
//    public BrowseAllAdapter(Activity activity, BrowseAllClickListener listener) {
//        super(Story.DIFF_CALLBACK);
//        this.mSearchClickListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public BrowseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = LayoutInflater.from(parent.getContext()).inflate(
//                R.layout.list_search,
//                parent,
//                false   );
//        return new BrowseViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BrowseViewHolder holder, int position) {
//
//        if (position <= -1) {  return; }
//        Story stories = getItem(position);
//        assert stories != null;
//        final String storystate = stories.getStorystate();
//        final String storycity = stories.getStorycity();
//        final String storytitle = stories.getAudiotitle();
//
//        try {holder.txtState.setText(storystate);
//            holder.txtCity.setText(storycity);
//            holder.txtTitle.setText(storytitle);
//
//        } catch (Exception e) {
//            e.printStackTrace();  }
//
//    }
//
//    public interface BrowseAllClickListener {
//        void onBrowseAllClickListener(int itemId);
//    }
//
//
//
//    public class BrowseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private final TextView txtState;
//        private final TextView txtCity;
//        private final TextView txtTitle;
//
//
//        public BrowseViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            txtTitle=itemView.findViewById(R.id.searchstorytitletext_view);
//            txtState = itemView.findViewById(R.id.searchstorystatetext_view);
//            txtCity= itemView.findViewById(R.id.searchstorycitytext_view);
//            TextView txtPlaySearch = itemView.findViewById(R.id.search_play_textview);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            int elementId = Objects.requireNonNull(getItem(getAdapterPosition())).getPrimaryId();
//            mSearchClickListener.onBrowseAllClickListener(elementId);
//        }
//    }
//}