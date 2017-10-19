package com.leo.test.Presentation.Category.Implementation;

import android.content.Context;

import com.leo.test.Models.Itunes;
import com.leo.test.Presentation.Category.Interfaces.ICategoryInteractor;
import com.leo.test.Presentation.Category.Interfaces.ICategoryPresenter;
import com.leo.test.Presentation.Category.Interfaces.ICategoryView;
import com.leo.test.lib.GreenRobotEventBus;


public class CategoryPresenter implements ICategoryPresenter {
    private ICategoryView view;
    private ICategoryInteractor interactor;
    private GreenRobotEventBus eventBus;
    private Context context;

    public CategoryPresenter(ICategoryView view) {
        this.view=view;
        interactor=new CategoryInteractor() ;
        this.eventBus= GreenRobotEventBus.getInstance();

    }

    @Override
    public void getCategory(Context context) {
        this.context=context;
        interactor.getCategory(context);
    }

    @Override
    public void getCategoryFromPersistence(Context context) {
        interactor.getCategoryFromPersistence(context);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void onGetTemplateSuccess(Itunes itunes) {
        view.notifyGetTemplateSuccess(itunes);
    }

    @Override
    public void onEventMainThread(Object object) {
        if(object instanceof Itunes) {
            onGetTemplateSuccess((Itunes) object);
        }else if(object instanceof String){
            view.notifyFromPersistence();
           //
        }
    }
}
