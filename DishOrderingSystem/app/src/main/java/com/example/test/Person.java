package com.example.test;

/**
 * Created by aaronhu on 5/14/16.
 */
public class Person {
    private String name;
    private String password;

    public Person(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return name;
    }

}
