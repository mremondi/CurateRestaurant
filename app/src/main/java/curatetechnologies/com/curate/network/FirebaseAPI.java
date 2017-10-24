package curatetechnologies.com.curate.network;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import curatetechnologies.com.curate.models.Manager;
import curatetechnologies.com.curate.models.Order;

/**
 * Created by mremondi on 9/27/17.
 */

public enum FirebaseAPI {

    SHARED;

    /** USER ROUTES **/
    public void addUserToDatabase(FirebaseUser user){
        Manager manager = new Manager(user.getEmail(), "");
        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).setValue(manager);
    }

    public void setRestaurantDeviceToken(String restaurantID, String refreshedToken) {
        FirebaseDatabase.getInstance().getReference()
                .child("restaurants")
                .child(restaurantID)
                .child("deviceToken")
                .setValue(refreshedToken);
    }



    public DatabaseReference getUserRestaurantRef(String userID){
        return FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("restaurantID");
    }

    /** ORDER ROUTES **/

    public DatabaseReference getNewOrdersRef(String restaurantID){
        return FirebaseDatabase.getInstance().getReference().child("restaurants").child(restaurantID).child("new_orders");
    }

    public DatabaseReference getCurrentOrdersRef(String restaurantID){
        return FirebaseDatabase.getInstance().getReference().child("restaurants").child(restaurantID).child("current_orders");
    }

    public DatabaseReference getCompletedOrdersRef(String restaurantID){
        return FirebaseDatabase.getInstance().getReference().child("restaurants").child(restaurantID).child("completed_orders");
    }

    public DatabaseReference getOrderItemsRef(DatabaseReference orderRef){
        return orderRef.child("order_items");
    }

    public void moveNewOrderToCurrentOrder(String restaurantID, DatabaseReference orderRef, Order order){
        DatabaseReference newRef = getCurrentOrdersRef(restaurantID);
        newRef.child(orderRef.getKey()).setValue(order);

        orderRef.removeValue();
    }

    public void saveOrderHistory(Order order){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("order_history");
        ref.setValue(order);
    }

    public void moveCurrentOrderToCompletedOrders(String restaurantID, DatabaseReference orderRef, Order order){
        DatabaseReference newRef = getCompletedOrdersRef(restaurantID);
        newRef.child(orderRef.getKey()).setValue(order);

        orderRef.removeValue();
    }
}
