package com.example.musicplayer;

import static com.example.musicplayer.R.id.list1;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    Button play,pause,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       ListView listView =this.<ListView>findViewById(list1);

        // Sample list of songs (you can replace this with your own)
        final String[] songs = {"Song 1", "Song 2", "Song 3"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, songs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playSong(position);
            }
        });
    }

    private void playSong(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        // Load and start playing the selected song
        mediaPlayer = MediaPlayer.create(this, getSongResource(position));
        {
            play=(Button) this.<View>findViewById(R.id.play);
            pause=(Button) this.<View>findViewById(R.id.pause);
            stop=(Button) this.<View>findViewById(R.id.stop);
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();

                }
            });
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.pause();
                }
            });
            stop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                }
            });
        }
        boolean isPlaying = true;
    }

    private int getSongResource(int position) {
        // Map the position to the resource ID of the song
        switch (position) {
            case 0:
                return R.raw.music1;

            // Replace with your actual resource IDs
            case 1:
                return R.raw.chogadha;
            case 2:
                return R.raw.makhna;
            default:
                return 0; // Handle invalid position
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}