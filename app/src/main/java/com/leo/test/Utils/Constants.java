package com.leo.test.Utils;

public class Constants {

    public interface Connection {
        int UNKNOW_CODE = 800;
        int TIMEOUT = 10000;
        String API_BASE_URL = "https://itunes.apple.com/us/rss/toppaidapplications";
        String GET_20 ="/limit=20/json" ;
    }

    public interface HttpMethodsType {
        String POST = "POST";
        String GET = "GET";
        String PUT = "PUT";
        String DELETE = "DELETE";
    }
    public interface Tags {
        String categorias="categorias";
        String aplicaciones="aplicaciones";
        String detalle="detalle";
    }


}
