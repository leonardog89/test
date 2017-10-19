package com.leo.test.Presentation.Base;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseModel implements Serializable {

    public String toJsonString() {
        return new Gson().toJson(this);
    }

    public static boolean isModelArray(String json) {
        try {
            new JSONArray(json);
        } catch (JSONException jsonException) {
            return false;
        }
        return true;
    }

    public static BaseModel objectFromJson(String json, Class<? extends BaseModel> type) throws com.google.gson.JsonParseException {
        return new Gson().fromJson(json, type);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<? extends BaseModel> arrayFromJson(String json, Class<? extends BaseModel> type) throws com.google.gson.JsonParseException, JSONException {
        ArrayList objectArray = new ArrayList();
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            String jsonObject = jsonArray.get(i).toString();
            objectArray.add(BaseModel.objectFromJson(jsonObject, type));
        }

        return objectArray;
    }
}
