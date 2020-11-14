package com.example.android.kstories.user;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kstories.R;
import com.example.android.kstories.model.AppDatabase;
import com.example.android.kstories.model.AppExecutors;
import com.example.android.kstories.model.Story;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class UserRecordAudioActivity extends AppCompatActivity {

    private static MediaRecorder mediaRecorder;
    private static MediaPlayer mediaPlayer;

    private String audioFilePath;
    private Button mStopButton;
    private Button mPlayButton;
    private Button mRecordButton;
    private Button mEditButton;
    private Button mPauseButton;
    private Button mResumeButton;

    private SeekBar mSeekBar;
    private int mInterval = 10;
    private Handler mHandler;
    private Runnable mRunnable;

    private TextView mPass;
    private TextView mDuration;
    private TextView mDue;

    TextView textView;
    CountDownTimer countDownTimer;
    int second = -1, minute, hour;



    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static final String LOG_TAG = "Record_Log";

    private boolean isRecording = false;

    //Firebase Instance Variable
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mAudioStorageReference;


    // Member variable for the Database
    private AppDatabase mDb;
    Button mButton;
    TextInputEditText mEditT, mEditState;
    private int mTaskId = DEFAULT_TASK_ID;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_record_audio);

        initViews();

        // Initialize member variable for the data base
        mDb = AppDatabase.getInstance(getApplicationContext());


        //Intialize Firebase Components
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseStorage = FirebaseStorage.getInstance();

//        //Firebase References
//        mAudioStorageReference = mFirebaseStorage.getReference().child
//                ("k_audio");

        // Create a storage reference from our app
    mAudioStorageReference = mFirebaseStorage.getReference().child("k_audio").child(customFilepath());



        audioSetup();



        ActivityCompat.requestPermissions(this, permissions,
                REQUEST_RECORD_AUDIO_PERMISSION);


    }

    private void initViews() {
        mEditT=findViewById(R.id.story_title);
        mEditState=findViewById(R.id.story_state);

        mButton = findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    //Create file path for file uploaded into Firebase Storage
    private String customFilepath() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US);
        String date = dateFormat.format(new Date());

        return date + ".3pg";
    }


    private void uploadAudioToFirebaseStorage() throws FileNotFoundException {

        InputStream stream = new FileInputStream(new File(audioFilePath));

        UploadTask uploadTask = mAudioStorageReference.putStream(stream);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(UserRecordAudioActivity.this, "No need to Firebase Console", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new
                                        OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                // taskSnapshot.getMetadata() contains file metadata such as
                                                //size, content-type, etc.
                                                        // ...
                                                        // Create file metadata including the content type
                StorageMetadata metadata = new StorageMetadata.Builder()
                        .setContentType("audio/3pgg")
                        //.setCustomMetadata("myCustomProperty", "myValue")
                        .build();

        Toast.makeText(UserRecordAudioActivity.this, "Check Firebase Console", Toast.LENGTH_SHORT).show();

                                            }
                                        });
    }



    public void playButton(View view) throws IOException {



        mPlayButton.setEnabled(false);
        mRecordButton.setEnabled(false);
        mStopButton.setEnabled(true);


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(audioFilePath);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    public void editButton(View view) throws IOException {



        Intent recordAudioIntent = new Intent(UserRecordAudioActivity.this, UserAudioDetailActivity.class);
        startActivity(recordAudioIntent);
    }



    public void recordButton(View view) {

        isRecording = true;
        mStopButton.setEnabled(true);
        mPlayButton.setEnabled(false);
        mRecordButton.setEnabled(false);


        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(
                    MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(audioFilePath);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
        } catch (Exception e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        mediaRecorder.start();
        showTimer();

    }




    public void stopButton(View view) {
        countDownTimer.cancel();
        mStopButton.setEnabled(false);
        mPlayButton.setEnabled(true);
        second = -1;
        minute = 0;
        hour = 0;
        textView.setText("00:00:00");

        if (isRecording)
        {

            mRecordButton.setEnabled(false);
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
        } else {
            mediaPlayer.release();
            mediaPlayer = null;
            mRecordButton.setEnabled(true);
        }

        try {
            uploadAudioToFirebaseStorage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void audioSetup()
    {
        textView = (TextView) findViewById(R.id.text);
        mRecordButton = (Button) findViewById(R.id.record_button);
        mPlayButton = (Button) findViewById(R.id.play_button);
        mStopButton = (Button) findViewById(R.id.stop_button);

        if (!hasMicrophone())
        {
            mStopButton.setEnabled(false);
            mPlayButton.setEnabled(false);
            mRecordButton.setEnabled(false);
        } else {
            mPlayButton.setEnabled(false);
            mStopButton.setEnabled(false);

        }

        // Record to the external cache directory for visibility
        audioFilePath = getExternalCacheDir().getAbsolutePath();
        audioFilePath += "/audioagain.3gp";

    }



    /***check if android device has microphone***/
    protected boolean hasMicrophone() {
        PackageManager pmanager = this.getPackageManager();
        return pmanager.hasSystemFeature(
                PackageManager.FEATURE_MICROPHONE);
    }

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }


    //display recording time
    public void showTimer() {
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                second++;
                textView.setText(recorderTime());
            }
            public void onFinish() {

            }
        };
        countDownTimer.start();
    }

    //recorder time
    public String recorderTime() {
        if (second == 60) {
            minute++;
            second = 0;
        }
        if (minute == 60) {
            hour++;
            minute = 0;
        }
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        // COMPLETED (5) Create a description variable and assign to it the value in the edit text
        //String description = mEditText.getText().toString();
        String audiotitle = mEditT.getText().toString();
        String storystate = mEditState.getText().toString();


        //Create a date variable and assign to it the current Date
        Date date = new Date();


        // COMPLETED (4) Make taskEntry final so it is visible inside the run method
        final Story taskEntry = new Story(audiotitle,storystate, date);
        // COMPLETED (2) Get the diskIO Executor from the instance of AppExecutors and
        // call the diskIO execute method with a new Runnable and implement its run method
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                // COMPLETED (9) insert the task only if mTaskId matches DEFAULT_TASK_ID
                // Otherwise update it
                // call finish in any case
                if (mTaskId == DEFAULT_TASK_ID) {
                    // insert new task
                    mDb.storyDao().insertTask(taskEntry);
                } else {
                    //update task
                    taskEntry.setUserId(mTaskId);
                    mDb.storyDao().updateTask(taskEntry);
                }
                finish();
            }
        });
    }

}