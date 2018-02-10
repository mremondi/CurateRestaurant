package curatetechnologies.com.curate.network;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import curatetechnologies.com.curate.models.Firebase.FirebaseRestaurantManager;
import curatetechnologies.com.curate.models.Firebase.FirebaseOrder;

/**
 * Created by mremondi on 9/27/17.
 */

public enum FirebaseAPI {

    SHARED;

    /** USER ROUTES **/
    public void addUserToDatabase(FirebaseUser user){
        FirebaseRestaurantManager firebaseRestaurantManager = new FirebaseRestaurantManager(user.getEmail(), "");
        FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid()).setValue(firebaseRestaurantManager);
    }

    public void setRestaurantIDForUser(String restaurantId, String userId){
        FirebaseDatabase.getInstance().getReference().child("users").child(userId).child("restaurantID").setValue(restaurantId);
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

    public DatabaseReference getRejectedOrdersRef(){
        return FirebaseDatabase.getInstance().getReference().child("rejected_orders");
    }

    public DatabaseReference getCompletedOrdersRef(String restaurantID){
        return FirebaseDatabase.getInstance().getReference().child("restaurants").child(restaurantID).child("completed_orders");
    }

    public DatabaseReference getOrderItemsRef(DatabaseReference orderRef){
        return orderRef.child("order_items");
    }

    public void rejectNewOrder(DatabaseReference orderRef, FirebaseOrder firebaseOrder){
        DatabaseReference rejectedRef = getRejectedOrdersRef();
        rejectedRef.child(orderRef.getKey()).setValue(firebaseOrder);

        orderRef.removeValue();
    }

    public void moveNewOrderToCurrentOrder(String restaurantID, DatabaseReference orderRef, FirebaseOrder firebaseOrder){
        DatabaseReference currentOrdersRef = getCurrentOrdersRef(restaurantID);
        currentOrdersRef.child(orderRef.getKey()).setValue(firebaseOrder);

        orderRef.removeValue();
    }

    public void saveOrderHistory(DatabaseReference orderRef, FirebaseOrder firebaseOrder){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("order_history").child(orderRef.getKey());
        ref.setValue(firebaseOrder);
    }

    public void moveCurrentOrderToCompletedOrders(String restaurantID, DatabaseReference orderRef, FirebaseOrder firebaseOrder){
        DatabaseReference completedOrdersRef = getCompletedOrdersRef(restaurantID);
        completedOrdersRef.child(orderRef.getKey()).setValue(firebaseOrder);

        orderRef.removeValue();
    }
}
