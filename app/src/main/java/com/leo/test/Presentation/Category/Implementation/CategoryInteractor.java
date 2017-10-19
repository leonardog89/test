package com.leo.test.Presentation.Category.Implementation;

import android.content.Context;

import com.leo.test.Presentation.Category.Interfaces.ICategoryInteractor;
import com.leo.test.Repository.Production.ItunesRepositoryImpl;
import com.leo.test.Repository.Production.ItunsRepository;

public class CategoryInteractor implements ICategoryInteractor{

    private ItunsRepository itunsRepository;


    public CategoryInteractor() {
        itunsRepository=new ItunesRepositoryImpl();
    }

    @Override
    public void getCategory(Context context) {
       itunsRepository.getInfo(context);

    }

    @Override
    public void getCategoryFromPersistence(Context context) {
        itunsRepository.getInfoFromPersistence(context);
    }

}
