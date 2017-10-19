package com.leo.test.Presentation.AplicationDetail.Implementation;

import android.content.Context;

import com.leo.test.Presentation.AplicationDetail.Interfaces.IAplicationInteractor;
import com.leo.test.Repository.Production.ItunesRepositoryImpl;
import com.leo.test.Repository.Production.ItunsRepository;


public class AplicationInteractor implements IAplicationInteractor {
    private ItunsRepository itunsRepository;

    public AplicationInteractor() {
        itunsRepository=new ItunesRepositoryImpl();
    }

    @Override
    public void getAplicationByTitle(String title,Context context) {
        itunsRepository.getAplicationByTitle(title,context);
    }

    @Override
    public void getAplicationByTitleFromPersistence(String title,Context context) {
        itunsRepository.getAplicationByTitleFromRepository(title,context);

    }
}
