package com.leo.test.Presentation.AplicationDetail.Interfaces;

import android.content.Context;

/**
 * Created by leonardo on 14/08/16.
 */
public interface IAplicationInteractor {
    void getAplicationByTitle(String title,Context context);
    void getAplicationByTitleFromPersistence(String title,Context context);
}
