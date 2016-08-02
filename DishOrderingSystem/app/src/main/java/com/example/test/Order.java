package com.example.test;

/**
 * Created by aaronhu on 5/10/16.
 */
public class Order {
    private int tablename;
    private String dish_name;
    private String price;
    private int dish_pic;
    private String dish_status;
    private String chef_name;

    public Order(int tablename,String dish_name, String price,int dish_pic, String dish_status, String chef_name){
        this.dish_name = dish_name;
        this.tablename = tablename;
        this.price = price;
        this.dish_pic = dish_pic;
        this.dish_status =dish_status;
        this.chef_name = chef_name;
    }

    public int getTableName(){
        return tablename;
    }

    public String getDish_name(){
        return dish_name;
    }

    public String getPrice(){
        return price;
    }

    public int getDish_pic(){
        return dish_pic;
    }

    public String getDish_status(){
        return dish_status;
    }

    public String getChef_name() {
        return chef_name;
    }
}
