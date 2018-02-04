package com.example.ceegan.ceegan_subbook;


/**
 * Represents a subscription
 */

public class subscription {
    private String name;
    private String date;
    private float cost;
    private String comment;

    /**
     * Constructs a subscription with name, date, cost, and comment
     *
     * @param name Name of the subscription
     * @param date Date of the subscription
     * @param cost Cost of the subscription
     * @param comment Optional comment for subscription
     */
    public subscription(String name, String date, float cost, String comment){
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.comment = comment;
    }

    /**
     * Gets name of subscription
     *
     * @return String of subscription name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name of the subscription
     *
     * @param name string representing the name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Returns the date of the subscription
     *
     * @return String of subscription date
     */
    public String getDate(){
        return date;
    }

    /**
     * Sets the subscription date
     *
     * @param date String representing the date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * gets the cost of the subscription
     *
     * @return float representing cost
     */
    public float getCost(){
        return cost;
    }

    /**
     * Sets the cost of the subscription
     *
     * @param cost float of cost
     */
    public void setCost(float cost){
        this.cost = cost;
    }

    /**
     * Returns the comment for the subscription
     *
     * @return String representing the comment
     */
    public String getComment(){
        return comment;
    }

    /**
     * Sets the comment for the subscription
     *
     * @param comment String representing comment
     */
    public void setComment(String comment){
        this.comment = comment;
    }
}
