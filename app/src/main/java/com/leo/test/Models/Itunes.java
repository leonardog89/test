package com.leo.test.Models;


import com.leo.test.Presentation.Base.BaseModel;

public class Itunes extends BaseModel {

    private ItunesFeed feed;

    public Itunes() {
    }

    public ItunesFeed getFeed() {
        return feed;
    }

    public void setFeed(ItunesFeed feed) {
        this.feed = feed;
    }
}
