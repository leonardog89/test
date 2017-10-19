package com.leo.test.Repository.Production;

import android.content.Context;

import com.leo.test.Models.Itunes;
import com.leo.test.Models.ItunesEntry;
import com.leo.test.Models.Response;
import com.leo.test.Repository.Connections.IProxyListener;
import com.leo.test.Repository.Connections.Proxy;
import com.leo.test.Repository.Persistence.Document;
import com.leo.test.Utils.Constants;
import com.leo.test.lib.EventBus;
import com.leo.test.lib.GreenRobotEventBus;

import java.util.ArrayList;


public class ItunesRepositoryImpl implements ItunsRepository,IProxyListener{
    private Document document;
    private Context context;
    String category;
    String title;
    public static Boolean dialog_show=false;

    @Override
    public void getInfo(Context context) {
        this.context=context;
        new Proxy<>(context, this, Itunes.class,Constants.Tags.categorias)
                .execute(Constants.Connection.GET_20,
                        Constants.HttpMethodsType.GET, null);

    }
    @Override
    public void getInfoFromPersistence(Context context) {
        this.context=context;
        document=new Document();
        Itunes itunes=(Itunes)document.getJsonFromDocument(context);
        EventBus eventBus= GreenRobotEventBus.getInstance();
        eventBus.post(itunes);

    }
    @Override
    public void getAplicationByCayegory(String category,Context context) {
        this.category=category;
        new Proxy<>(context, this, Itunes.class,Constants.Tags.aplicaciones)

                .execute(Constants.Connection.GET_20,
                        Constants.HttpMethodsType.GET, null);
    }

    @Override
    public void getAplicationFromRespository(String category,Context context) {
        document=new Document();
        Itunes itunes=(Itunes)document.getJsonFromDocument(context);
        ArrayList<ItunesEntry>list=new ArrayList<>();
        for(int i=0;i<itunes.getFeed().getEntry().size();i++){
            if(itunes.getFeed().getEntry().get(i).getCategory().getAttributes().getLabel().equals(category)){
                list.add(itunes.getFeed().getEntry().get(i));
            }
        }
        EventBus eventBus= GreenRobotEventBus.getInstance();
        eventBus.post(list);
    }

    @Override
    public void getAplicationByTitle(String title,Context context) {
        this.title=title;

        new Proxy<>(context, this, Itunes.class,Constants.Tags.detalle)
                .execute(Constants.Connection.GET_20,
                        Constants.HttpMethodsType.GET, null);
    }

    @Override
    public void getAplicationByTitleFromRepository(String title,Context context) {
        document=new Document();
        Itunes itunes=(Itunes)document.getJsonFromDocument(context);
        ItunesEntry aplication=new ItunesEntry();
        for(int i=0;i<itunes.getFeed().getEntry().size();i++){
            if(itunes.getFeed().getEntry().get(i).getTitle().getLabel().equals(title)){
                aplication=itunes.getFeed().getEntry().get(i);
            }
        }
        EventBus eventBus= GreenRobotEventBus.getInstance();
        eventBus.post(aplication);
    }

    //respuesta de peticiones http//
    @Override
    public void onProxyResponse(Object objectResponse, String tag) {
        dialog_show=false;
        sendEvent(objectResponse,tag);
    }

    //fallo a peticiones htttp//
    @Override
    public void onFailedResponse(Response response,String tag) {
        if(!dialog_show) {
            String error = "error";
            EventBus eventBus = GreenRobotEventBus.getInstance();
            eventBus.post(error);
            dialog_show=true;
        }
        else{
            document=new Document();
            Itunes itunes=(Itunes)document.getJsonFromDocument(context);
            sendEvent(itunes,tag);
        }
    }

    //este evento guarda la informacion en un domumento de texto y luego gestiona la peticion//
    public void sendEvent(Object objectResponse, String tag){
        Itunes itunes=(Itunes) objectResponse;
        document=new Document();
        document.saveJsonToDocument(itunes.toJsonString());
        EventBus eventBus= GreenRobotEventBus.getInstance();
        switch (tag){
            case Constants.Tags.categorias:
                eventBus.post(itunes);
                break;
            case Constants.Tags.aplicaciones:
                ArrayList<ItunesEntry>list=new ArrayList<>();
                for(int i=0;i<itunes.getFeed().getEntry().size();i++){
                    if(itunes.getFeed().getEntry().get(i).getCategory().getAttributes().getLabel().equals(category)){
                        list.add(itunes.getFeed().getEntry().get(i));
                    }
                }

                eventBus.post(list);
                break;
            case Constants.Tags.detalle:
                ItunesEntry aplication=new ItunesEntry();
                for(int i=0;i<itunes.getFeed().getEntry().size();i++){
                    if(itunes.getFeed().getEntry().get(i).getTitle().getLabel().equals(title)){
                        aplication=itunes.getFeed().getEntry().get(i);
                    }
                }
                eventBus.post(aplication);
                break;
        }
    }
}
