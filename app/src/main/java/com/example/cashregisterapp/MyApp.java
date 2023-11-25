package com.example.cashregisterapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    ArrayList<History> appHistorylist = new ArrayList<>(0);
    public ArrayList<History> getAppHistorylist() {
        return appHistorylist;
    }

    ArrayList<Product> appProductlist;

    public ArrayList<Product> getAppProductlist() {

        if(appProductlist==null)
        {
            appProductlist = new ArrayList<>(5);
            appProductlist.add(new Product("Shirts",14.67,22));
            appProductlist.add(new Product("Pants",210.77,10));
            appProductlist.add(new Product("Hats",30,15));
            appProductlist.add(new Product("Jeans",2000,20));

        }
return  appProductlist;
    }


}



