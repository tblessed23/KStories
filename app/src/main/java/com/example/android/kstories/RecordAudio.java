package com.example.android.kstories;


//public class RecordAudio {
//    private boolean isRecording = false;
//    private static MediaRecorder mediaRecorder;
//    private static MediaPlayer mediaPlayer;
//
//    private String audioFilePath;
//    private static final String LOG_TAG = "Record_Log";
//    TextView textView;
//    CountDownTimer countDownTimer;
//    int second = -1, minute, hour;
//
//    public UserRecordAudioActivity activity;
//    private Button mStopButton = (Button) this.activity.findViewById(R.id.stop_button);
//    private Button mPlayButton = (Button) this.activity.findViewById(R.id.play_button);
//    private Button mRecordButton = (Button) this.activity.findViewById(R.id.record_button);
////.... other attributes
//
//    public RecordAudio( UserRecordAudioActivity _activity){
//
//        this.activity = _activity;
////other initializations...
//
//    }
//
//    public void playButton(View view) throws IOException {
//
//
//
//        mPlayButton.setEnabled(false);
//        mRecordButton.setEnabled(false);
//        mStopButton.setEnabled(true);
//
//
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setDataSource(audioFilePath);
//        mediaPlayer.prepare();
//        mediaPlayer.start();
//    }
//
//
//
//
//    public void recordButton(View view) {
//
//        isRecording = true;
//        mStopButton.setEnabled(true);
//        mPlayButton.setEnabled(false);
//        mRecordButton.setEnabled(false);
//
//
//        try {
//            mediaRecorder = new MediaRecorder();
//            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//            mediaRecorder.setOutputFormat(
//                    MediaRecorder.OutputFormat.THREE_GPP);
//            mediaRecorder.setOutputFile(audioFilePath);
//            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            mediaRecorder.prepare();
//        } catch (Exception e) {
//            Log.e(LOG_TAG, "prepare() failed");
//        }
//        mediaRecorder.start();
//        showTimer();
//
//    }
//
//
//
//
//    public void stopButton(View view) {
//        countDownTimer.cancel();
//        mStopButton.setEnabled(false);
//        mPlayButton.setEnabled(true);
//        second = -1;
//        minute = 0;
//        hour = 0;
//        textView.setText("00:00:00");
//
//        if (isRecording)
//        {
//
//            mRecordButton.setEnabled(false);
//            mediaRecorder.stop();
//            mediaRecorder.release();
//            mediaRecorder = null;
//            isRecording = false;
//        } else {
//
//            mediaPlayer.release();
//            mediaPlayer = null;
//            mRecordButton.setEnabled(true);
//        }
//
////        try {
////            uploadAudioToFirebaseStorage();
////        } catch (FileNotFoundException e) {
////            e.printStackTrace();
////        }
//    }
//
//    //display recording time
//    public void showTimer() {
//        countDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                second++;
//                textView.setText(recorderTime());
//            }
//            public void onFinish() {
//
//            }
//        };
//        countDownTimer.start();
//    }
//
//    //recorder time
//    public String recorderTime() {
//        if (second == 60) {
//            minute++;
//            second = 0;
//        }
//        if (minute == 60) {
//            hour++;
//            minute = 0;
//        }
//        return String.format("%02d:%02d:%02d", hour, minute, second);
//    }
//
//}
