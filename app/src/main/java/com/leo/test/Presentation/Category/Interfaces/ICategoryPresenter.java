package com.leo.test.Presentation.Category.Interfaces;

import android.content.Context;

import com.leo.test.Models.Itunes;

public interface ICategoryPresenter {
    void getCategory(Context context);
    void getCategoryFromPersistence(Context context);
    void onCreate();
    void onDestroy();
    void onGetTemplateSuccess(Itunes itunes);
    void onEventMainThread(Object object);
}
