package com.example.gorenganindonesia.Util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GreetingGenerator {

    public static String generateGreeting() {
        // Get the current time
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTimeStr = sdf.format(currentTime);

        // Extract hours and minutes
        int hours = Integer.parseInt(currentTimeStr.split(":")[0]);
        int minutes = Integer.parseInt(currentTimeStr.split(":")[1]);

        // Generate greeting based on the time
        if (hours >= 6 && hours < 12) {
            return "Selamat Pagi";
        } else if (hours >= 12 && hours < 15) {
            return "Selamat Siang";
        } else if (hours >= 15 && hours < 18) {
            return "Selamat Sore";
        } else {
            return "Selamat Malam";
        }
    }
}
