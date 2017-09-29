package curatetechnologies.com.curate.network;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mremondi on 9/27/17.
 */

public enum FirebaseAPI {

    SHARED;

    public DatabaseReference getOrderQueueRef(){
        return FirebaseDatabase.getInstance().getReference().child("order_queue");
    }
}
