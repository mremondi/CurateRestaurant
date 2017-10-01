package curatetechnologies.com.curate.models;

public class Order {

    // Necessary fields for our server and Stripe
    private String orderID;
    private String userID;
    private String restaurantID;
    // TODO: include any stripe tokens here

    private Integer price;
    private String timeToCompletion;
    private String username;

    public Order(){
        // needed for firebase
    }

    public Order(String orderID, String userID, String restaurantID, Integer price, String timeToCompletion, String username) {
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.price = price;
        this.timeToCompletion = timeToCompletion;
        this.username = username;
    }


    public String getUsername(){ return this.username; }

    public String getPrice(){
        return String.valueOf(this.price);
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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTimeToCompletion(String timeToCompletion) {
        this.timeToCompletion = timeToCompletion;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
