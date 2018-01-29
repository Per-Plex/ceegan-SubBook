package com.example.ceegan.ceegan_subbook;

import java.util.Date;

/**
 * Created by Ceegan on 2018-01-22.
 */

public class subscription {
    private String name;
    private String date;
    private float cost;
    private String comment;

    public subscription(String name, String date, float cost, String comment){
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.comment = comment;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public float getCost(){
        return cost;
    }

    public void setCost(float cost){
        this.cost = cost;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }
}
