package com.leo.test.Models;


import com.google.gson.annotations.SerializedName;
import com.leo.test.Models.Entry.Atribute;
import com.leo.test.Models.Entry.EntryArtist;
import com.leo.test.Models.Entry.EntryCategory;
import com.leo.test.Models.Entry.EntryImage;
import com.leo.test.Models.Entry.EntryPrice;
import com.leo.test.Models.Entry.EntryRelease;
import com.leo.test.Models.Entry.Label;
import com.leo.test.Presentation.Base.BaseModel;

import java.util.ArrayList;

public class ItunesEntry extends BaseModel {

    @SerializedName("im:name")
    private Label name;
    @SerializedName("im:image")
    private ArrayList <EntryImage> images;
    @SerializedName("im:price")
    private EntryPrice price;
    @SerializedName("im:contentType")
    private Atribute contentType;
    private Label title;
    private EntryCategory category;
    private Label summary;
    private  Label rights;
    @SerializedName("im:artist")
    private EntryArtist artist;
    @SerializedName("im:releaseDate")
    private EntryRelease release;

    public ItunesEntry() {
    }

    public EntryRelease getRelease() {
        return release;
    }

    public void setRelease(EntryRelease release) {
        this.release = release;
    }

    public EntryArtist getArtist() {
        return artist;
    }

    public void setArtist(EntryArtist artist) {
        this.artist = artist;
    }

    public Atribute getContentType() {
        return contentType;
    }

    public void setContentType(Atribute contentType) {
        this.contentType = contentType;
    }

    public EntryPrice getPrice() {
        return price;
    }

    public void setPrice(EntryPrice price) {
        this.price = price;
    }

    public ArrayList<EntryImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<EntryImage> images) {
        this.images = images;
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public EntryCategory getCategory() {
        return category;
    }

    public void setCategory(EntryCategory category) {
        this.category = category;
    }

    public Label getSummary() {
        return summary;
    }

    public void setSummary(Label summary) {
        this.summary = summary;
    }

    public Label getRight() {
        return rights;
    }

    public void setRight(Label right) {
        this.rights = right;
    }
}
