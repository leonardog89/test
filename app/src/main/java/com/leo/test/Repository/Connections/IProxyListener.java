package com.leo.test.Repository.Connections;

import com.leo.test.Models.Response;

public interface IProxyListener {

    void onProxyResponse(Object objectResponse,String tag);
    void onFailedResponse(Response response,String tag);
}
