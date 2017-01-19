package com.example.goku.miwokudacityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        ArrayList<String> words = new ArrayList<String>();

        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");

        words.add("2one");
        words.add("2two");
        words.add("2three");
        words.add("2four");
        words.add("2five");
        words.add("2six");
        words.add("2seven");
        words.add("2eight");
        words.add("2nine");
        words.add("2ten");

        words.add("3one");
        words.add("3two");
        words.add("3three");
        words.add("3four");
        words.add("3five");
        words.add("3six");
        words.add("3seven");
        words.add("3eight");
        words.add("3nine");
        words.add("3lten");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);


        /*
        for (int i = 0; i < words.size(); i++) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(i));
            rootView.addView(wordView);
        }
        */
        /*
        int i = 0;
        while (i < words.size()) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(i));
            rootView.addView(wordView);
            i++;
        }
        */


    }
}
