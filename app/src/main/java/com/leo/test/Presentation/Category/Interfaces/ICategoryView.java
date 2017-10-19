package com.leo.test.Presentation.Category.Interfaces;

import com.leo.test.Models.Itunes;


public interface ICategoryView {
    void notifyGetTemplateSuccess(Itunes itunes);
    void notifyFromPersistence();
}
