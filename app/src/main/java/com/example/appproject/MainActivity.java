package com.example.appproject;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private TextView viewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.your_song);
        viewText = findViewById(R.id.ViewText);

        Button playMusicButton = findViewById(R.id.playMusicButton);
        playMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        Button stopMusicButton = findViewById(R.id.stopMusicButton);
        stopMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });
    }

    private void playMusic() {
        try {
            if (mediaPlayer != null) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    viewText.setText("Music is playing");
                }
            }
        } catch (IllegalStateException e) {
            handleMediaPlayerException(e);
        }
    }

    private void stopMusic() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    viewText.setText("Music stopped");
                }
            }
        } catch (IllegalStateException e) {
            handleMediaPlayerException(e);
        }
    }

    private void handleMediaPlayerException(IllegalStateException e) {
        e.printStackTrace();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
