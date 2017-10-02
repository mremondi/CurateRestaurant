package curatetechnologies.com.curate.network;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import curatetechnologies.com.curate.models.Order;

/**
 * Created by mremondi on 9/27/17.
 */

public enum FirebaseAPI {

    SHARED;

    public DatabaseReference getNewOrdersRef(){
        return FirebaseDatabase.getInstance().getReference().child("order_queue");
    }

    public DatabaseReference getCurrentOrdersRef(){
        return FirebaseDatabase.getInstance().getReference().child("current_orders");
    }

    public DatabaseReference getCompletedOrdersRef(){
        return FirebaseDatabase.getInstance().getReference().child("completed_orders");
    }

    public DatabaseReference getOrderItemsRef(DatabaseReference orderRef){
        return orderRef.child("order_items");
    }

    public void moveNewOrderToCurrentOrder(DatabaseReference orderRef, Order order){
        DatabaseReference newRef = getCurrentOrdersRef();
        newRef.child(orderRef.getKey()).setValue(order);

        //orderRef.removeValue();

    }

    public void moveCurrentOrderToCompletedOrders(DatabaseReference orderRef, Order order){
        DatabaseReference newRef = getCompletedOrdersRef();
        newRef.child(orderRef.getKey()).setValue(order);

        //orderRef.removeValue();
    }
}
