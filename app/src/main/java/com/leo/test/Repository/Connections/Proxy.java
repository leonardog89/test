package com.leo.test.Repository.Connections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;


import com.leo.test.Models.Response;
import com.leo.test.Presentation.Base.BaseModel;
import com.leo.test.R;
import com.leo.test.Utils.Constants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Proxy<T extends BaseModel> extends AsyncTask<String, Integer, String> {

    private final IProxyListener proxyListener;
    private final Class<T> type;
    private final Context context;
    private final String tag;

    public Proxy(Context context, IProxyListener proxyListener, Class<T> type,String tag) {
        this.proxyListener = proxyListener;
        this.context = context;
        this.type = type;
        this.tag=tag;
    }


    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String response = null;
        if (!isNetworkAvailable(context)) {

            return new Response(Constants.Connection.UNKNOW_CODE,
                    context.getString(R.string.whitout_conexion)).toJsonString();
        }

        try {
            urlConnection = doConnection(params[0], params[1],  params[2]);

            response = getResponse(urlConnection);
        } catch (IOException e) {
            response = new Response(Constants.Connection.UNKNOW_CODE,
                    context.getString(R.string.not_response_server)).toJsonString();
        } catch (Exception e) {
            response = new Response(Constants.Connection.UNKNOW_CODE,
                    context.getString(R.string.unknown_response)).toJsonString();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();

            if (response == null)
                response = new Response(Constants.Connection.UNKNOW_CODE,
                        context.getString(R.string.not_response_server)).toJsonString();
        }

        return response;
    }

    private HttpURLConnection doConnection(String uri, String methodType, String body) throws IOException {
        String http = Constants.Connection.API_BASE_URL.concat(uri);
        DataOutputStream printout;
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(http).openConnection();

        urlConnection.setRequestMethod(methodType);


        if (methodType.equals(Constants.HttpMethodsType.POST) ||
                methodType.equals(Constants.HttpMethodsType.PUT)) {
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setConnectTimeout(Constants.Connection.TIMEOUT);
            urlConnection.setReadTimeout(Constants.Connection.TIMEOUT);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.connect();

            printout = new DataOutputStream(urlConnection.getOutputStream());
            printout.write(body.getBytes("UTF-8"));
            printout.flush();
            printout.close();
        }

        return urlConnection;
    }

    private String getResponse(HttpURLConnection urlConnection) throws IOException {
        StringBuilder sb = new StringBuilder();
        int httpResult = urlConnection.getResponseCode();
        String response;

        if (httpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            response = sb.toString();

        }  else {
            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();

                Response proxyResponse;

                proxyResponse = (Response) Response.objectFromJson(sb.toString(), Response.class);
                proxyResponse.setStatusCode(httpResult);
                response = proxyResponse.toJsonString();

        }

        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.e("onPost", response);
        Response proxyResponse;
        if(response==null){
            proxyResponse = new Response(Constants.Connection.UNKNOW_CODE,
                    context.getString(R.string.unknown_response));
            proxyListener.onFailedResponse(proxyResponse,tag);
            return;
        }
        try {
            BaseModel model=BaseModel.objectFromJson(response,Response.class);
            Response response1=(Response) model;
            if(response1.getMessage().equals("")){
                model=BaseModel.objectFromJson(response,type);
                proxyListener.onProxyResponse(model,tag);
                return;
            }
            proxyListener.onFailedResponse((Response) model,tag);
            return;
        }catch (Exception e){

            BaseModel model=BaseModel.objectFromJson(response,type);
            proxyListener.onProxyResponse(model,tag);

            return;
        }

    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo==null){
            return false;
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
