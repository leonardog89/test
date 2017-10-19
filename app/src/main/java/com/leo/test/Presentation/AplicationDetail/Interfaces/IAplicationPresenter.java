package com.leo.test.Presentation.AplicationDetail.Interfaces;

import android.content.Context;

import com.leo.test.Models.ItunesEntry;

/**
 * Created by leonardo on 14/08/16.
 */
public interface IAplicationPresenter {
    void onCreate();
    void onDestroy();
    void getAplicationByTitle(String title,Context context);
    void getAplicationByTitleFromPersistence(String title,Context context);
    void ongetAplicationSucces(ItunesEntry aplication);
    void onEventMainThread(Object o);
}
