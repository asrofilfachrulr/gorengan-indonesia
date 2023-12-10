package com.example.gorenganindonesia.Model.Factories;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RequestBodyFactory {
    public static RequestBody createJSONFormData(Object yourObject) {
        Gson gson = new Gson();
        String jsonData = gson.toJson(yourObject);

        Log.i("GSON RESULT", jsonData);

        // Create the JSON part
        RequestBody jsonPart = RequestBody.create(MediaType.parse("application/json"), jsonData);

        // Create the MultipartBody
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(MultipartBody.Part.createFormData("json", "data.json", jsonPart));

        return builder.build();
    }
}
