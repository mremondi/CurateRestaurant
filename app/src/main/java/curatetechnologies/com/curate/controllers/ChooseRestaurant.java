package curatetechnologies.com.curate.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import curatetechnologies.com.curate.MainActivity;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.adapters.SelectMenuAdapter;
import curatetechnologies.com.curate.controllers.adapters.SelectRestaurantAdapter;
import curatetechnologies.com.curate.models.Curate.CurateRestaurant;
import curatetechnologies.com.curate.network.CurateAPI;
import curatetechnologies.com.curate.network.CurateConnection;
import curatetechnologies.com.curate.network.FirebaseAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseRestaurant extends AppCompatActivity {

    @BindView(R.id.select_restaurant_recycler_view)
    RecyclerView restaurantRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_restaurant);

        ButterKnife.bind(this);

        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String managerID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final CurateAPI api = CurateConnection.setUpRetrofit();
        Call<ArrayList<CurateRestaurant>> call = api.getRestaurantsByManagerID(managerID);

        final ChooseRestaurant self = this;
        call.enqueue(new Callback<ArrayList<CurateRestaurant>>() {
            @Override
            public void onResponse(Call<ArrayList<CurateRestaurant>> call, Response<ArrayList<CurateRestaurant>> response) {
                if (response.body() != null) {
                    Log.d("RESPONSE, " ,response.body().toString());
                    restaurantRecyclerView.setAdapter(new SelectRestaurantAdapter(self, response.body()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CurateRestaurant>> call, Throwable t) {
                Log.d("FAILURE", "Message " + t.getMessage());
            }
        });
    }

    public void saveRestaurantID(final int restaurantId){
        // UPDATE AND SAVE CHOSEN RESTAURANT ID LOCALLY
        SharedPreferences.Editor editor = getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE).edit();
        editor.putInt("restaurantID", restaurantId);
        editor.apply();
        updateDeviceToken(restaurantId);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // UPDATE AND SAVE CHOSEN RESTAURANT ID ON FIREBASE
        FirebaseAPI.SHARED.setRestaurantIDForUser(String.valueOf(restaurantId), user.getUid());

        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
    private void updateDeviceToken(final int restaurantID){
        FirebaseAPI.SHARED.setRestaurantDeviceToken(String.valueOf(restaurantID), FirebaseInstanceId.getInstance().getToken());
    }
}
