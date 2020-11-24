package com.example.android.kstories.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kstories.R;
import com.example.android.kstories.model.Favorites;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    // Class variables for the List that holds task data and the Context
    private List<Favorites> mTaskEntries;
    private Context mContext;


    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param Reviews the ItemClickListener
     */
    public FavoritesAdapter(Context context, ArrayList<Favorites> Reviews) {
        mContext = context;
        mTaskEntries = Reviews;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new FavoritesViewHolder that holds the view for each task
     */
    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.favorite_layout, parent, false);

        return new FavoritesViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        // Determine the values of the wanted data
        Favorites taskEntry = mTaskEntries.get(position);
        String titleFavorities = taskEntry.getTitleFavorites();
        int id = taskEntry.getId();


        //Set values
        holder.taskDescriptionView.setText(titleFavorities);
        holder.updatedAtView.setText(String.valueOf(id));


    }



    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mTaskEntries == null) {
            return 0;
        }
        return mTaskEntries.size();
    }

    /**Used for uodating/deleting database information**/
    public List<Favorites> getTasks() {
        return mTaskEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<Favorites> taskEntries) {
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }


    // Inner class for creating ViewHolders
    class FavoritesViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView taskDescriptionView;
        TextView updatedAtView;


        /**
         * Constructor for the FavoritesViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public FavoritesViewHolder(View itemView) {
            super(itemView);

            taskDescriptionView = itemView.findViewById(R.id.taskDescription);
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
        }
    }
}

