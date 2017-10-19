package com.leo.test.Presentation.AplicationList.Interfaces;

import android.content.Context;

import com.leo.test.Models.ItunesEntry;

import java.util.ArrayList;

public interface IAplicationListPresenter {
    void onCreate();
    void onDestroy();
    void getAplicationByCategory(String category,Context context);
    void getAplicationFromPesistence(String category,Context context);
    void getAplicationSuccess(ArrayList<ItunesEntry> entryArrayList);
    void onEventMainThread(Object ooject);
}
