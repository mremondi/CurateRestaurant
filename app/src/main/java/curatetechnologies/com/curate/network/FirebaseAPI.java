package curatetechnologies.com.curate.network;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import curatetechnologies.com.curate.models.Order;

/**
 * Created by mremondi on 9/27/17.
 */

public enum FirebaseAPI {

    SHARED;

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

        //orderRef.removeValue();

    }

    public void moveCurrentOrderToCompletedOrders(String restaurantID, DatabaseReference orderRef, Order order){
        DatabaseReference newRef = getCompletedOrdersRef(restaurantID);
        newRef.child(orderRef.getKey()).setValue(order);

        //orderRef.removeValue();
    }

    public DatabaseReference getUserRestaurantRef(String userID){
        return FirebaseDatabase.getInstance().getReference().child("users").child(userID).child("restaurantID");
    }

}
