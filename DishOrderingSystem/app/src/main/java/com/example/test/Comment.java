package com.example.test;

/**
 * Created by aaronhu on 5/13/16.
 */
public class Comment {

    private String person_name;
    private String comment;
    private String date;

    public Comment(String person_name, String comment, String date){

        this.person_name = person_name;
        this.comment = comment;
        this.date = date;

    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getPerson_name() {
        return person_name;
    }
}
