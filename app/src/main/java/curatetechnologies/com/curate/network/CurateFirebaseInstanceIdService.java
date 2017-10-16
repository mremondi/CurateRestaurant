package curatetechnologies.com.curate.network;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mremondi on 10/13/17.
 */

public class CurateFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyAndroidFCMIIDService";

    @Override
    public void onTokenRefresh() {
        //Get hold of the registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            SharedPreferences prefs = getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
            String restaurantID = prefs.getString("restaurantID", "");//"No name defined" is the default value.
            FirebaseAPI.SHARED.setRestaurantDeviceToken(restaurantID, refreshedToken);

        }
    }
}