package com.leo.test.Presentation.AplicationList.Implementation;

import android.content.Context;

import com.leo.test.Models.ItunesEntry;
import com.leo.test.Presentation.AplicationList.Interfaces.IAplicationListInteractor;
import com.leo.test.Presentation.AplicationList.Interfaces.IAplicationListPresenter;
import com.leo.test.Presentation.AplicationList.Interfaces.IAplicationsView;
import com.leo.test.lib.GreenRobotEventBus;

import java.util.ArrayList;

public class AplicationListPresenter implements IAplicationListPresenter {

    private IAplicationsView view;
    private IAplicationListInteractor interactor;
    private GreenRobotEventBus eventBus;

    public AplicationListPresenter(IAplicationsView view) {
        this.view=view;
        interactor=new AplicationListInteractor();
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
    public void getAplicationByCategory(String category,Context context) {
        interactor.getAplicationByCategory(category,context);
    }

    @Override
    public void getAplicationFromPesistence(String category,Context context) {
        interactor.getAplicationByCategoryFromPersistence(category, context);
    }

    @Override
    public void getAplicationSuccess(ArrayList<ItunesEntry> entryArrayList) {
        view.onGetAplicationSuccess(entryArrayList);
    }

    @Override
    public void onEventMainThread(Object object) {
        if(object instanceof ArrayList) {
            ArrayList<ItunesEntry> list=(ArrayList<ItunesEntry>)object;
            getAplicationSuccess(list);
        }else if(object instanceof String){
            view.notifyFromPersistence();
            //
        }

    }
}
