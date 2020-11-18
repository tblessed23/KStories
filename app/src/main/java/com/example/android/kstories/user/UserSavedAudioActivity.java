package com.example.android.kstories.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import com.example.android.kstories.model.MainViewModel;
import com.example.android.kstories.model.Story;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class UserSavedAudioActivity extends AppCompatActivity implements UserStoryAdapter.ItemClickListener  {
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
        setContentView(R.layout.activity_saved_user_audio);

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


        //Initialize Database
        mDb = AppDatabase.getInstance(getApplicationContext());

        setupViewModel();

    }
    private void setupViewModel() {

        //MainViewModel viewModel2 = ViewModelProvider(this).get(MainViewModel.class);
        MainViewModel viewModel=new ViewModelProvider((ViewModelStoreOwner) this).get(MainViewModel.class);
        viewModel.getTasks().observe(this, new Observer<List<Story>>() {
            @Override
            public void onChanged(@Nullable List<Story> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                mAdapter.setTasks(taskEntries);
            }
        });
    }



//    private void downloadfile() {
//
//
//
//                FirebaseStorage storage = FirebaseStorage.getInstance();
//                StorageReference storageRef = storage.getReferenceFromUrl("gs://kstories-900ec.appspot.com").child("k_audio");

//                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener() {
//                    @Override
//                   public void onSuccess(Uri uri) {
//                        Toast.makeText(UserSavedAudioActivity.this, "sucess" + uri.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("Main", "File uri: " + uri.toString());
//                    }
//                });

//                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                   public void onSuccess(Uri uri) {
//                        // Got the download URL for 'users/me/profile.png'
//                        //Toast.makeText(UserAudioActivity.this, "sucess" + uri.toString(), Toast.LENGTH_SHORT).show();
//                        Log.i("Main", "File uri: " + uri.toString());
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception exception) {
//                        // Handle any errors
//                        Toast.makeText(UserSavedAudioActivity.this, "no sucess", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }

    @Override
    public void onItemClickListener(int itemId) {
        // Launch AddTaskActivity adding the itemId as an extra in the intent

    }
}