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
public class ColorsFragment extends Fragment {

    /**
     * Objeto MediaPlayer para reproducir audio
     */
    private MediaPlayer mediaPlayer;

    /**
     * Clase anonima que libera el objeto MediaPlayer, se guarda en una variable para que solo
     * se cree una solo vez y después de reutilize
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        public void onCompletion (MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    /**
     * Objeto AudioManager para acceder al foco del audio
     */
    private AudioManager audioManager;

    /**
     * Listener que es llamado cada vez que se actualiza el foco del audio
     */
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //Recobramos el foco
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //Perdimos el foco.
                //El usuario cambio a otra app
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                //Perdimos el foco temporalmente.
                //Posiblemnete entro una llamada pero el foco regresara
                mediaPlayer.pause();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //Otra app esta reproducioendo audio sobre nosotros, pero podemos seguir reproduciendo audio.
                //Entro una notificación, podemos bajar el volumen,
                mediaPlayer.pause();
            }
        }
    };
    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //Log.v("ColorsActivity", "onCreate callback");

        //Obtener el servicio AUDIO_SERVICE
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("sam sam", "serafin", R.drawable.color_black, R.raw.silvia));

        WordAdapter listAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listItem = (ListView)rootView.findViewById(R.id.list);
        listItem.setAdapter(listAdapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                 * Liberar espacio antes de crear el objeto MediaPlayer si existe actualmente, ya que
                 * podriamos estar apunto de reproducir un audio diferente. Esto ocurre si por ejemplo
                 * el usuario da click en muchas palabras y aun no terminan de reproducirse.
                 */
                releaseMediaPlayer();

                /**
                 * Solicitud para pedir el foco del audio
                 */
                int resultAudioManager = audioManager.requestAudioFocus(
                        onAudioFocusChangeListener,//Listener para ser avisador cuando se actualize el foco del audio
                        AudioManager.STREAM_MUSIC,//Afectamos el stream music
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT//El audio a reproducir es corto
                );
                /**
                 * Si la solicitud de audio es exitosa
                 */
                if (resultAudioManager == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Word word = words.get(position);
                    //Log.v("ColorsActivity", "Current word: " + word);
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();

                    /**
                     * Listener que libera la memoria una vez que el audio a finalizado, se debe crear
                     * despues del método start()
                     *
                     * Se debe de tener cuidado en no ocupar mucha memoria, por ello se debe liberar la memoria
                     * ocupada por el objeto MediaPlayer en el callback onCompletion.
                     *
                     * Para no crear una clase anonima cada vez que se libere el objeto MediaPlayer, la creamos una
                     * solo vez y la guardamos en el campo mCompletionListener
                     *
                     * Código a re emlpezar:
                     *
                     * mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                     *
                     * @Override
                     * public void onCompletion(MediaPlayer mp) {
                     *  releaseMediaPlayer();
                     * }
                     *
                     * Por el código:
                     */
                    mediaPlayer.setOnCompletionListener(mCompletionListener);
                } else if (resultAudioManager == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                    Log.v("ColorsActivity", "-->>AUDIOFOCUS_REQUEST_FAILED");
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

            //Liberar espacio de solicitud de audio
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
