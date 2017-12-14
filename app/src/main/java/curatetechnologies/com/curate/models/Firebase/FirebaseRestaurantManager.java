package curatetechnologies.com.curate.models.Firebase;

/**
 * Created by mremondi on 10/10/17.
 */

public class FirebaseRestaurantManager {

    private String email;
    private String restaurantID;

    public FirebaseRestaurantManager(){}

    public FirebaseRestaurantManager(String email, String restaurantID){
        this.email = email;
        this.restaurantID = restaurantID;
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

}
