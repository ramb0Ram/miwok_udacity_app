package com.example.goku.miwokudacityapp;

/**
 * Created by goku on 26/01/17.
 */

public class Word {

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;

    /**
     *
     * @param mDefaultTranslation
     * @param mMiwokTranslation
     */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int audioResourceId){
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceId = audioResourceId;
    }

    /**
     *
     * @param mDefaultTranslation is the worlds in the language that the user
     * @param mMiwokTranslation is the word in the Miwok language
     * @param mImageResourceId is the drawable resource ID for the image asset
     */
    public Word(String mDefaultTranslation, String mMiwokTranslation, int mImageResourceId, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = mImageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return this.mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
