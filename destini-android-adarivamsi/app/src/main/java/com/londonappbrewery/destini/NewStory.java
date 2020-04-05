package com.londonappbrewery.destini;

/**
 * Created by adari on 6/6/2017.
 */

public class NewStory {

    private int mstory;
    private int moption1,moption2;

    public NewStory(int storyresourceid  ){
        mstory = storyresourceid;

    }

    public int getMstory() {
        return mstory;
    }

    public void setMstory(int mstory) {
        this.mstory = mstory;
    }

}


