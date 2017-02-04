package com.example.goku.miwokudacityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti"));
        words.add(new Word("two", "ottiko"));
        words.add(new Word("three", "tolookosu"));
        words.add(new Word("four", "oyyisa"));
        words.add(new Word("five", "massoka"));
        words.add(new Word("six", "temmokka"));
        words.add(new Word("seven", "kenekaku"));
        words.add(new Word("eight", "kawinta"));
        words.add(new Word("nine", "woé"));
        words.add(new Word("ten", "naáacha"));

        words.add(new Word("one 2", "lutti"));
        words.add(new Word("two 2", "ottiko"));
        words.add(new Word("three 3", "tolookosu"));
        words.add(new Word("four 4", "oyyisa"));
        words.add(new Word("five 5", "massoka"));
        words.add(new Word("six 6", "temmokka"));
        words.add(new Word("seven 7", "kenekaku"));
        words.add(new Word("eight 8", "kawinta"));
        words.add(new Word("nine 9", "woé"));
        words.add(new Word("ten 10", "naáacha"));

        WordAdapter itemsAdapter = new WordAdapter(this, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }
}
