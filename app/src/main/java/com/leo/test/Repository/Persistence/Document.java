package com.leo.test.Repository.Persistence;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.leo.test.Models.Itunes;
import com.leo.test.Presentation.Base.BaseModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

/**
 * Created by leonardo on 13/08/16.
 */
public class Document {
    public void saveJsonToDocument(String datos) {

        try
        {
            File dir = new File(Environment.getExternalStorageDirectory() + "/Grability/");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, "datos.txt");
            OutputStreamWriter bf = new OutputStreamWriter(new FileOutputStream(file));
            bf.write(datos);
            bf.close();

        }
        catch (Exception ex)
        {
            Log.e("Splash", "Error al escribir fichero a tarjeta SD");
            ex.printStackTrace();
        }
    }
    public BaseModel getJsonFromDocument(Context context) {
        //  HTTP request
        String json = "";
        try {
            File dir = new File(Environment.getExternalStorageDirectory() + "/Grability/");
            File f = new File(dir.getAbsolutePath(), "datos.txt");
            FileReader fReader = new FileReader(f);
            BufferedReader reader = new BufferedReader(fReader);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            json = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("JsonParserBuffer", "Error converting result " + e.toString());
        }
        BaseModel model=BaseModel.objectFromJson(json, Itunes.class);
        return model;

    }
}
