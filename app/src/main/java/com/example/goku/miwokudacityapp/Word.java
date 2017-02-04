package com.example.goku.miwokudacityapp;

/**
 * Created by goku on 26/01/17.
 */

public class Word {

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    public Word(String mDefaultTranslation, String mMiwokTranslation){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return this.mMiwokTranslation;
    }
}
