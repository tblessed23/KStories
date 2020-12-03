package com.example.android.kstories.search;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kstories.BrowseAllAdapter;
import com.example.android.kstories.NowPlaying;
import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.Story;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    // Constant for date format
    private static final String DATE_FORMAT = "MM/dd/yyy";

    // Member variable to handle item clicks
    // final private ItemClickListener mItemClickListener;
    // Class variables for the List that holds task data and the Context
    private List<Story> mStoryEntries;
    private Context mContext;
    // UserEditViewModel userModel;
    private int mTaskId = DEFAULT_TASK_ID;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Member variable for the Database
    private AppDatabase mDb;


    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     *
     */
    public SearchAdapter(Context context) {
        mContext = context;
        //mItemClickListener = listener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.search_list, parent, false);

        return new SearchAdapter.SearchViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(final SearchAdapter.SearchViewHolder holder, final int position) {
        // Determine the values of the wanted data
        final Story stories = mStoryEntries.get(position);
        final String title = stories.getAudiotitle();
        final String ancestorfn = stories.getAncestorfirstname();

        mDb = AppDatabase.getInstance(mContext);



        //Set values
        holder.titleView.setText(title);
        holder.ancestorFNView.setText(ancestorfn);

        //Handle Editing Database Entry
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int elementId = mStoryEntries.get(position).getUserId();
                Intent intent = new Intent(mContext, NowPlaying.class);
                intent.putExtra(NowPlaying.EXTRA_TASK_ID, elementId);
                mContext.startActivity(intent);
            }
        });



        //holder.audioURLView.setText(audiourl);

    }




    /*


    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mStoryEntries == null) {
            return 0;
        }
        return mStoryEntries.size();
    }

    public List<Story> getTasks() {
        return mStoryEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<Story> storyEntries) {
        mStoryEntries = storyEntries;
        notifyDataSetChanged();
    }

    // Inner class for creating ViewHolders
    class SearchViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView titleView;
        TextView ancestorFNView;
        ImageButton arrow;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public SearchViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.storytitle_text_view);
            //updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
            ancestorFNView= itemView.findViewById(R.id.ancestorfirstname_text_view);
            arrow= itemView.findViewById(R.id.browse_all_button);


        }

    }

}
