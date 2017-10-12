package com.example.goku.miwokudacityapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vpRoot);
        adapterViewPager = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

    }

    public static class MyPageAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 4;

        public MyPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ColorsFragment();
                case 1:
                    return new FamilyMembersFragment();
                case 2:
                    return new NumbersFragment();
                case 3:
                    return new PhrasesFragment();
                default:
                    return null;
            }
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        /**
         * This method may be called by the ViewPager to obtain a title string
         * to describe the specified page. This method may return null
         * indicating no title for this page. The default implementation returns
         * null.
         *
         * @param position The position of the title requested
         * @return A title for the requested page
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Colors";
                case 1:
                    return "Family";
                case 2:
                    return "Numbers";
                case 3:
                    return "Phrases";
                default:
                    return null;
            }
        }
    }
}
