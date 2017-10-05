package com.example.goku.miwokudacityapp;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {


    public PhrasesFragment() {
        // Required empty public constructor
    }

    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer){
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            Log.v("PhrasesActivity", "-->>ENTRANDO A onAudioFocusChange");
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //Recobramos el foco
                Log.v("PhrasesActivity", "-->>AUDIOFOCUS_GAIN");
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                //Perdimos el foco temporalmente
                Log.v("PhrasesActivity", "-->>AUDIOFOCUS_LOSS_TRANSIENT");
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //Perdimos el foco totalmente
                Log.v("PhrasesActivity", "-->>AUDIOFOCUS_LOSS");
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //Perdimos foco pero podemos seguir reproduciendo audio y opcionalmentye bajar el volumen
                Log.v("PhrasesActivity", "-->>AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                mediaPlayer.pause();
            } else {
                Log.v("PhrasesActivity", "-->>NINGUN CALLBACK");
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        //WordAdapter adapterPhrases = new WordAdapter(this, words, R.color.category_phrases);
        WordAdapter adapterPhrases = new WordAdapter(getActivity(), words, R.color.category_phrases);

        //ListView list = (ListView) findViewById(R.id.list);
        ListView list = (ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapterPhrases);

        //audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int resultRequestAudioFocus = audioManager.requestAudioFocus(
                        onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,//Afactamos el stream music
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT//Reproducimos el audio en un tiempo corto
                );
                if (resultRequestAudioFocus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    releaseMediaPlayer();
                    Word word = words.get(position);
                    //mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(completionListener);
                    Log.v("PhrasesActivity", "-->>AUDIOFOCUS_REQUEST_GRANTED");
                } else if (resultRequestAudioFocus == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                    Log.v("PhrasesActivity", "-->>AUDIOFOCUS_REQUEST_FAILED");
                }
            }
        });


        return rootView;
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;

            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
