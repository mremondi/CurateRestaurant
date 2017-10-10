package curatetechnologies.com.curate.models;

import java.util.ArrayList;

public class Order {

    // Necessary fields for our server and Stripe
    private String orderID;
    private String userID;
    private String restaurantID;
    // TODO: include any stripe tokens here

    private String price;
    private String timeToCompletion;
    private String startingTime;
    private String completedTime;
    private String username;

    private ArrayList<MenuItem> order_items;

    public Order(){
        // needed for firebase
    }

    public Order(String orderID, String userID, String restaurantID, String price, String timeToCompletion, String username, ArrayList<MenuItem> order_items) {
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.price = price;
        this.timeToCompletion = timeToCompletion;
        this.username = username;
        this.order_items = order_items;
    }


    public String getUsername(){ return this.username; }

    public String getPrice(){
        return this.price;
    }

    public String getOrderID(){
        return this.orderID;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getRestaurantID(){
        return this.restaurantID;
    }

    public String getTimeToCompletion(){
        return this.timeToCompletion;
    }

    public ArrayList<MenuItem> getOrder_items(){
        return this.order_items;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTimeToCompletion(String timeToCompletion) {
        this.timeToCompletion = timeToCompletion;
    }

    public void setOrder_items(ArrayList<MenuItem> order_items){
        this.order_items = order_items;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
        this.completedTime = completedTime;
    }
}

