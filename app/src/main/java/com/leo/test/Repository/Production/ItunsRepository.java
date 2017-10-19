package com.leo.test.Repository.Production;

import android.content.Context;


public interface ItunsRepository {
    void getInfo(Context context);
    void getInfoFromPersistence(Context context);
    void getAplicationByCayegory(String category,Context context);
    void getAplicationFromRespository(String category,Context context);
    void getAplicationByTitle(String title,Context context);
    void getAplicationByTitleFromRepository(String title,Context context);
}
