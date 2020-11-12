package com.example.android.kstories.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Story;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class UserSavedAudioActivity extends AppCompatActivity implements UserStoryAdapter.ItemClickListener {
   //Constant for logging
    private static final String TAG = MainActivity.class.getSimpleName();
    // Member variables for the adapter and RecyclerView
    private RecyclerView mRecyclerView;
    private UserStoryAdapter mAdapter;

    // COMPLETED (1) Create AppDatabase member variable for the Database
    private AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_audio);

//        Button button=findViewById(R.id.download);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                downloadfile();
//            }
//        });




        // Set the RecyclerView to its corresponding view
        mRecyclerView = findViewById(R.id.recyclerViewTasks);

        // Set the layout for the RecyclerView to be a linear layout, which measures and
        // positions items within a RecyclerView into a linear list
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter and attach it to the RecyclerView
        mAdapter = new UserStoryAdapter(this, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        /*
         Add a touch helper to the RecyclerView to recognize when a user swipes to delete an item.
         An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
         and uses callbacks to signal when a user is performing these actions.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete
                // COMPLETED (1) Get the diskIO Executor from the instance of AppExecutors and
                // call the diskIO execute method with a new Runnable and implement its run method
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        // COMPLETED (3) get the position from the viewHolder parameter
                        int position = viewHolder.getAdapterPosition();
                        List<Story> tasks = mAdapter.getTasks();
                        // COMPLETED (4) Call deleteTask in the taskDao with the task at that position
                        mDb.storyDao().deleteTask(tasks.get(position));
                        // COMPLETED (6) Call retrieveTasks method to refresh the UI
                        retrieveTasks();
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);

//

        //Initialize Database
        mDb = AppDatabase.getInstance(getApplicationContext());



    }
    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Story> tasks = mDb.storyDao().loadAllStories();
                // We will be able to simplify this once we learn more
                // about Android Architecture Components
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setTasks(tasks);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
      retrieveTasks();
    }

    @Override
    public void onItemClickListener(int itemId) {
        // Launch AddTaskActivity adding the itemId as an extra in the intent
//        Intent intent = new Intent(UserSavedAudioActivity.this, UserEditAudioDetailsActivity.class);
//        intent.putExtra(UserEditAudioDetailsActivity.EXTRA_TASK_ID, itemId);
//        startActivity(intent);
    }

//    private void downloadfile() {
//
//
//
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageRef = storage.getReferenceFromUrl("gs://kstories-900ec.appspot.com").child("k_audio");
//
////                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener() {
////                    @Override
////                    public void onSuccess(Uri uri) {
////                        Toast.makeText(UserAudioActivity.this, "sucess" + uri.toString(), Toast.LENGTH_SHORT).show();
////                        Log.i("Main", "File uri: " + uri.toString());
////                    }
////                });
//
//                storageRef.child("new_kaudio.3pg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//                        // Got the download URL for 'users/me/profile.png'
//                        //Toast.makeText(UserAudioActivity.this, "sucess" + uri.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("Main", "File uri: " + uri.toString());
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle any errors
//                        Toast.makeText(UserAudioActivity.this, "no sucess", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
}