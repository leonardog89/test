package com.leo.test.Presentation.AplicationDetail.Implementation;

import android.content.Context;

import com.leo.test.Models.ItunesEntry;
import com.leo.test.Presentation.AplicationDetail.Interfaces.IAplicationInteractor;
import com.leo.test.Presentation.AplicationDetail.Interfaces.IAplicationPresenter;
import com.leo.test.Presentation.AplicationDetail.Interfaces.IAplicationView;
import com.leo.test.lib.GreenRobotEventBus;


public class AplicationPresenter implements IAplicationPresenter {
    private IAplicationView view;
    private IAplicationInteractor interactor;
    private GreenRobotEventBus eventBus;

    public AplicationPresenter(IAplicationView view) {
        this.view=view;
        interactor=new AplicationInteractor();
        this.eventBus= GreenRobotEventBus.getInstance();
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
    public void getAplicationByTitle(String title,Context context) {
        interactor.getAplicationByTitle(title,  context);
    }

    @Override
    public void getAplicationByTitleFromPersistence(String title,Context context) {
        interactor.getAplicationByTitleFromPersistence(title,context);
    }

    @Override
    public void ongetAplicationSucces(ItunesEntry aplication) {
        view.notifyGetAplicationSuccess(aplication,true);
    }


    @Override
    public void onEventMainThread(Object o) {

                if(o instanceof ItunesEntry) {
                    ItunesEntry aplication=(ItunesEntry)o;
                    ongetAplicationSucces(aplication);
                    return;
                }else if (o instanceof String){
                    view.notifyFromPersistence();
                    return;
                }

    }
}
