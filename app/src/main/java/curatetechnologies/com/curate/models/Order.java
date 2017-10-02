package curatetechnologies.com.curate.models;

import java.util.ArrayList;

public class Order {

    // Necessary fields for our server and Stripe
    private Long orderID;
    private Long userID;
    private Long restaurantID;
    // TODO: include any stripe tokens here

    private String price;
    private String timeToCompletion;
    private String username;

    private ArrayList<MenuItem> order_items;

    public Order(){
        // needed for firebase
    }

    public Order(Long orderID, Long userID, Long restaurantID, String price, String timeToCompletion, String username, ArrayList<MenuItem> order_items) {
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

    public Long getOrderID(){
        return this.orderID;
    }

    public Long getUserID(){
        return this.userID;
    }

    public Long getRestaurantID(){
        return this.restaurantID;
    }

    public String getTimeToCompletion(){
        return this.timeToCompletion;
    }

    public ArrayList<MenuItem> getOrder_items(){
        return this.order_items;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setRestaurantID(Long restaurantID) {
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
}
