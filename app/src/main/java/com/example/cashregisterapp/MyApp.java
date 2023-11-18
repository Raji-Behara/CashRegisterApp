package com.example.cashregisterapp;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {



  ArrayList<Product> appProductlist;
  ArrayList<History> appHistorylist;


    public ArrayList<Product> getAppProductlist() {

        if(appProductlist==null)
        {
            appProductlist = new ArrayList<>(5);
            appProductlist.add(new Product("Shirts",14.67,22));
            appProductlist.add(new Product("Pants",20.77,10));
            appProductlist.add(new Product("Hats",30,5));
            appProductlist.add(new Product("Jeans",40,2));

        }
return  appProductlist;
    }


}



