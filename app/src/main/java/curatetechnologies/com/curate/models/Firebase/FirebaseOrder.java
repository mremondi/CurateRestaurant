package curatetechnologies.com.curate.models.Firebase;

import java.util.ArrayList;

import curatetechnologies.com.curate.models.Curate.CurateMenuItem;

public class FirebaseOrder {

    // Necessary fields for our server and Stripe
    private String orderID;
    private String userID;
    private String restaurantID;
    private String restaurantName;
    private String deviceID;

    private String price;
    private String timeToCompletion;
    private String startingTime;
    private String completedTime;
    private String username;
    private String fullName;
    private String profilePictureURL;
    private String instructions;

    private ArrayList<FirebaseMenuItem> order_items;

    public FirebaseOrder(){
        // needed for firebase
    }

    public FirebaseOrder(String orderID, String userID, String restaurantID, String restaurantName, String deviceID, String price, String timeToCompletion, String username, String fullName, String profilePictureURL, ArrayList<FirebaseMenuItem> order_items, String instructions) {
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

    public ArrayList<FirebaseMenuItem> getOrder_items(){
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

    public void setOrder_items(ArrayList<FirebaseMenuItem> order_items){
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

