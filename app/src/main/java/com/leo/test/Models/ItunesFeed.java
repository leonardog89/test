package com.leo.test.Models;


import com.leo.test.Presentation.Base.BaseModel;

import java.util.ArrayList;

public class ItunesFeed extends BaseModel {

    private ArrayList<ItunesEntry> entry;

    public ItunesFeed() {
    }

    public ArrayList<ItunesEntry> getEntry() {
        return entry;
    }

    public void setEntry(ArrayList<ItunesEntry> entry) {
        this.entry = entry;
    }
}
