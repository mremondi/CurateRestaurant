package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.network.CurateAPI;
import curatetechnologies.com.curate.network.CurateConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mremondi on 12/13/17.
 */

public class CloseShop extends Fragment {

    boolean isOpen = true;

    private Unbinder unbinder;

    @BindView(R.id.close_shop_btn) Button btnCloseShop;
    @OnClick(R.id.close_shop_btn) void openCloseShop(){
        final CurateAPI api = CurateConnection.setUpRetrofit();
        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        String restaurantID = prefs.getString("restaurantID", "");

        if (isOpen){
            btnCloseShop.setText("Reopen");
            btnCloseShop.setBackgroundColor(getResources().getColor(R.color.greenPastel));
            this.isOpen = false;
            callSetAvailability(api, restaurantID);

        }
        else{
            btnCloseShop.setText("Close Up");
            btnCloseShop.setBackgroundColor(getResources().getColor(R.color.primaryRed));
            this.isOpen = true;
            callSetAvailability(api, restaurantID);
        }
    }

    private void callSetAvailability(CurateAPI api, String restaurantID) {
        Call<Integer> call = api.setRestaurantAvailability(restaurantID, isOpen);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("SUCCESS", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("FAILURE", "onFailure: "  + t.getMessage());
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_close_shop, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}