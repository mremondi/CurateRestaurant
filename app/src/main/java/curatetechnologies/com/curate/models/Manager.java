package curatetechnologies.com.curate.models;

/**
 * Created by mremondi on 10/10/17.
 */

public class Manager {

    private String email;
    private String restaurantID;
    private String deviceToken;

    public Manager(){}

    public Manager(String email, String restaurantID, String deviceToken){
        this.email = email;
        this.restaurantID = restaurantID;
        this.deviceToken = deviceToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(String restaurantID) {
        this.restaurantID = restaurantID;
    }


    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }


}
