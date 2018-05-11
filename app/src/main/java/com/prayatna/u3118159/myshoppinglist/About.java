package com.prayatna.u3118159.myshoppinglist;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class About extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn = (Button)findViewById(R.id.buttonPlaybackOn);
        Typeface fontAwesome = FontManager.FONTAWESOME.typeface(getAssets());
        btn.setTypeface(fontAwesome);

        btn = (Button)findViewById(R.id.buttonStopPlayback);
        btn.setTypeface(fontAwesome);
    }


    //code from tutorial to play a video
    public void turnOnVideo(View view) {

        playVideo();

    }

    private void playVideo() {
        videoView = (VideoView) findViewById(R.id.videoView);
        // Create a progress bar for Video player and show it
        progressDialog = new ProgressDialog(this); // progress bar
        progressDialog.setMessage("Buffering...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);
            videoView.setMediaController(mediacontroller);
            // set the path to video file
            videoView.setVideoPath(
                    "android.resource://" + getPackageName() + "/"
                            + R.raw.sight);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                videoView.start();
            }
        });

    }

    public void turnOffVideo(View view) {

        if (videoView == null)
            return;
        if (videoView.isPlaying()){
            videoView.stopPlayback();
            videoView = null;
        }
        if (progressDialog.isShowing()){
            progressDialog.hide();
            progressDialog.dismiss();
            progressDialog = null;
        }

    }

}
