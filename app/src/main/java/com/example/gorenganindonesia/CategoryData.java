package com.example.gorenganindonesia;

import java.util.ArrayList;

public class CategoryData {
    public static ArrayList<String> generate(){
        ArrayList<String> categories = new ArrayList<>();

        categories.add("Semua");
        categories.add("Aci");
        categories.add("Kedelai");
        categories.add("Buah");
        categories.add("Kentang");
        categories.add("Ikan");
        categories.add("Isian Sayur");
        categories.add("Manis");

        return categories;
    }
}
