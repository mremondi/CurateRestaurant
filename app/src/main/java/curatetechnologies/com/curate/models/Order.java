package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 9/15/17.
 */

public class Order {

    // Necessary fields for our server and Stripe
    private String orderID;
    private String itemID;
    private String userID;
    private String restaurantID;
    // TODO: include any stripe tokens here


    // Convenience for UI
    private String itemName;
    private String price;
    private String timeToCompletion;

    public Order(){
        // needed for firebase
    }

    public Order(String orderID, String itemID, String userID, String restaurantID, String price, String timeToCompletion) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.userID = userID;
        this.restaurantID = restaurantID;
        this.price = price;
        this.timeToCompletion = timeToCompletion;
    }


    public String getItemName(){
        return this.itemName;
    }

}
