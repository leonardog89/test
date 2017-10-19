package com.leo.test.Presentation.AplicationDetail.Interfaces;

import com.leo.test.Models.ItunesEntry;

/**
 * Created by leonardo on 14/08/16.
 */
public interface IAplicationView {
    void notifyGetAplicationSuccess(ItunesEntry aplication,Boolean flag);
    void notifyFromPersistence();

}
