package com.leo.test.Presentation.AplicationList.Implementation;

import android.content.Context;

import com.leo.test.Presentation.AplicationList.Interfaces.IAplicationListInteractor;
import com.leo.test.Repository.Production.ItunesRepositoryImpl;
import com.leo.test.Repository.Production.ItunsRepository;

public class AplicationListInteractor implements IAplicationListInteractor {

    private ItunsRepository itunsRepository;
    public AplicationListInteractor() {
        itunsRepository=new ItunesRepositoryImpl();
    }

    @Override
    public void getAplicationByCategory(String category,Context context) {
        itunsRepository.getAplicationByCayegory(category, context);
    }

    @Override
    public void getAplicationByCategoryFromPersistence(String category,Context context) {
        itunsRepository.getAplicationFromRespository(category, context);
    }
}
