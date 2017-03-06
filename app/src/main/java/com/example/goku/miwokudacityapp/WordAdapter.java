package com.example.goku.miwokudacityapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goku on 26/01/17.
 */

public class WordAdapter extends ArrayAdapter<Word>{

    private int background_color;

    public WordAdapter(Activity context, ArrayList<Word> words, int backgroud_color){
        /**
         * El 0 indica que no necesitamos inflar la vista desde aquí, lo haremos en
         * el método getView()
         */
        super(context, 0, words);
        this.background_color = backgroud_color;
    }

    /**
     * position es la posición de la vista a crear
     * cobertView es la vista a reusar
     * parent es la vista padre
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Guarda la posición para volver a obtener el objeto dentro del listener
        listItemView.setTag(position);

        /**
         * El siguiente código tambien es correcto, pero no es el que se uso en el tutorial:
         *  LinearLayout listItemWrapText = (LinearLayout) listItemView.findViewById(R.id.list_item_wrap_text);
         *  listItemWrapText.setBackgroundResource(this.background_color);
         */

        /**
         * El siguiente código es el que se uso en el tutorial:
         */
        View textContainer = listItemView.findViewById(R.id.list_item_wrap_text);
        int color = ContextCompat.getColor(getContext(), this.background_color);
        textContainer.setBackgroundColor(color);


        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());


        ImageView image = (ImageView) listItemView.findViewById(R.id.image_of_word);
        if (currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageResourceId());
            image.setVisibility(View.VISIBLE);
        } else {
            image.setVisibility(View.GONE);
        }



        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag();
                Word currentLocalWord = getItem(position);
                Toast.makeText(getContext(), currentLocalWord.getDefaultTranslation(), Toast.LENGTH_SHORT).show();
            }
        });
        return listItemView;
    }
}
