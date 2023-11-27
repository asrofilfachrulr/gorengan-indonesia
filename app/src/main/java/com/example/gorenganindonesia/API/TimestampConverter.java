package com.example.gorenganindonesia.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimestampConverter {
    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        // Define the date format expected in the JSON
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        gsonBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, com.google.gson.JsonSerializationContext context) {
                return context.serialize(dateFormat.format(src));
            }
        });

        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context) {
                try {
                    return dateFormat.parse(json.getAsString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        return gsonBuilder.create();
    }
}
