package com.example.goku.miwokudacityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "ottiko", R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four));
        words.add(new Word("five", "massoka", R.drawable.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", "woé", R.drawable.number_nine));
        words.add(new Word("ten", "naáacha", R.drawable.number_ten));

        words.add(new Word("one 2", "lutti", R.drawable.number_one));
        words.add(new Word("two 2", "ottiko", R.drawable.number_two));
        words.add(new Word("three 2", "tolookosu", R.drawable.number_three));
        words.add(new Word("four 2", "oyyisa", R.drawable.number_four));
        words.add(new Word("five 2", "massoka", R.drawable.number_five));
        words.add(new Word("six 2", "temmokka", R.drawable.number_six));
        words.add(new Word("seven 2", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight 2", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine 2", "woé", R.drawable.number_nine));
        words.add(new Word("ten 2", "naáacha", R.drawable.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }
}
