package com.example.android.kstories.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Story;
import com.example.android.kstories.model.UserEditViewModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class UserStoryAdapter extends RecyclerView.Adapter<UserStoryAdapter.StoryViewHolder> {
    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";

    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;
    // Class variables for the List that holds task data and the Context
    private List<Story> mStoryEntries;
    private Context mContext;
    UserEditViewModel userModel;
   private AppDatabase mDb = AppDatabase.getInstance(mContext);
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param listener the ItemClickListener
     */
    public UserStoryAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.user_saved_audio_list, parent, false);

        return new StoryViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(final StoryViewHolder holder, final int position) {
        // Determine the values of the wanted data
        Story stories = mStoryEntries.get(position);
        final String title = stories.getAudiotitle();
        final String ancestorfn = stories.getAncestorfirstname();
        final String ancestorln = stories.getAncestorlastname();
        final String familyname = stories.getFamilyname();
        final String storycity = stories.getStorycity();
        final String storycounty = stories.getStorycounty();
        final String storystate = stories.getStorystate();
        final String updatedAt = dateFormat.format(stories.getUpdatedAt());

        holder.editStoryDetails.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int elementId = mStoryEntries.get(position).getUserId();
        Intent intent = new Intent(mContext, UserEditAudioDetailsActivity.class);
        intent.putExtra(UserEditAudioDetailsActivity.EXTRA_TASK_ID, elementId);
        mContext.startActivity(intent);
            }
        });

        holder.buttonViewOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating a popup menu
                PopupMenu popup = new PopupMenu(mContext, holder.buttonViewOption);
                //inflating menu from xml resource
                popup.inflate(R.menu.options_menu);
                //adding click listener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                //handle menu1 click

                                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        List<Story> tasks =  getTasks();
                                        mDb.storyDao().deleteTask(mStoryEntries.get(position));
                                    }
                                });
                                break;

                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();

            }
        });
        //Set values
        holder.titleView.setText(title);
        holder.storyStateView.setText(storystate);
        holder.storyCountyView.setText(storycounty);
        holder.storyCityView.setText(storycity);
        holder.familynameView.setText(familyname);
        holder.ancestorFNView.setText(ancestorfn);
        holder.ancestorLNView.setText(ancestorln);
        holder.updatedAtView.setText(updatedAt);

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

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description and priority TextViews
        TextView titleView;
        TextView ancestorFNView;
        TextView ancestorLNView;
        TextView familynameView;
        TextView storyCityView;
        TextView storyCountyView;
        TextView storyStateView;
        TextView updatedAtView;
        TextView buttonViewOption;
        Button editStoryDetails;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public StoryViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.audio_title);
            updatedAtView = itemView.findViewById(R.id.taskUpdatedAt);
            ancestorFNView= itemView.findViewById(R.id.ancestor_first_name);
            ancestorLNView= itemView.findViewById(R.id.ancestor_last_name);
            familynameView=itemView.findViewById(R.id.family_name);
            storyCityView=itemView.findViewById(R.id.city_of_story);
            storyCountyView=itemView.findViewById(R.id.county_of_story);
            storyStateView=itemView.findViewById(R.id.state_of_story);
            editStoryDetails=itemView.findViewById(R.id.edit_saved_audio);
            buttonViewOption = (TextView) itemView.findViewById(R.id.textViewOptions);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mStoryEntries.get(getAdapterPosition()).getUserId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}