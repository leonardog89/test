package com.leo.test.Presentation.AplicationList.Interfaces;

import android.content.Context;


public interface IAplicationListInteractor {
    void getAplicationByCategory(String category,Context context);
    void getAplicationByCategoryFromPersistence(String category,Context context);

}
