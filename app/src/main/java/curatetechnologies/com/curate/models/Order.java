package curatetechnologies.com.curate.models;

import java.util.ArrayList;

public class Order {

    // Necessary fields for our server and Stripe
    private Integer orderID;
    private Integer userID;
    private Integer restaurantID;
    private String restaurantName;
    private String deviceID;
    // TODO: include any stripe tokens here

    private Double price;
    private String timeToCompletion;
    private String startingTime;
    private String completedTime;
    private String username;
    private String fullName;
    private String profilePictureURL;
    private String instructions;

    private ArrayList<MenuItem> order_items;

    public Order(){
        // needed for firebase
    }

    public Order(Integer orderID, Integer userID, Integer restaurantID, String restaurantName, String deviceID, Double price, String timeToCompletion, String username, String fullName, String profilePictureURL, ArrayList<MenuItem> order_items, String instructions) {
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.price = price;
        this.timeToCompletion = timeToCompletion;
        this.username = username;
        this.fullName = fullName;
        this.profilePictureURL = profilePictureURL;
        this.order_items = order_items;
        this.instructions = instructions;
    }


    public String getUsername(){ return this.username; }

    public Double getPrice(){
        return this.price;
    }

    public Integer getOrderID(){
        return this.orderID;
    }

    public Integer getUserID(){
        return this.userID;
    }

    public Integer getRestaurantID(){
        return this.restaurantID;
    }

    public String getTimeToCompletion(){
        return this.timeToCompletion;
    }

    public ArrayList<MenuItem> getOrder_items(){
        return this.order_items;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setRestaurantID(Integer restaurantID) {
        this.restaurantID = restaurantID;
    }

    public void setPrice(Double price) {
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}

