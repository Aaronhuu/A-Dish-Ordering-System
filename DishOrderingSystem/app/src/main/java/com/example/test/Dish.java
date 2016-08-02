package com.example.test;

/**
 * Created by aaronhu on 5/7/16.
 */
public class Dish {
    private int uri;
    private String name;
    private String price;


    public Dish(int uri, String name, String price){
        this.uri = uri;
        this.name = name;
        this.price = price;
    }

    public int getURI(){
        return uri;
    }

    public String getDishName(){
        return name;
    }

    public String getPrice(){
        return price;
    }

}
