package com.leo.test.Presentation.AplicationList.Interfaces;

import com.leo.test.Models.ItunesEntry;

import java.util.ArrayList;


public interface IAplicationsView {
    void onGetAplicationSuccess(ArrayList<ItunesEntry> entryArrayList);
    void notifyFromPersistence();

}
