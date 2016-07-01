package com.kaplan.pdma.mediaplayerexample;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Boolean isPlaying = false;
    MediaPlayer mediaPlayer;
    int[] songs = {R.raw.s1, R.raw.s2, R.raw.s3, R.raw.s4};
    String[] titles = {"Skyfall", "I want to move it2",
            "Writing's on the wall", "Daddy by psy"};
    int songId = 0; //first song in the list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        Button buttonNextSong = (Button) findViewById(R.id.buttonNextSong);
        final TextView textViewSongTitle = (TextView) findViewById(R.id.textViewSongTitle);

        mediaPlayer = MediaPlayer.create(this, songs[songId]);
        textViewSongTitle.setText(titles[songId]);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying == false) {
                    isPlaying = true;
                    buttonPlay.setText("Pause");
                    mediaPlayer.start();
                } else {
                    isPlaying = false;
                    buttonPlay.setText("Play");
                    mediaPlayer.pause();
                }
            }
        });

        buttonNextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying) {
                    isPlaying = false;
                    buttonPlay.setText("Play");
                    mediaPlayer.stop();
                }

                songId = (songId+1) % songs.length;
                mediaPlayer = MediaPlayer.create(getApplicationContext(), songs[songId]);
                textViewSongTitle.setText(titles[songId]);

                //play the song
                isPlaying = true;
                buttonPlay.setText("Pause");
                mediaPlayer.start();
            }
        });
    }
}
