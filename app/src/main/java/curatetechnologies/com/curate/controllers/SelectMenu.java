package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.adapters.SelectMenuAdapter;
import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.network.CurateAPI;
import curatetechnologies.com.curate.network.CurateConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mremondi on 10/2/17.
 */

public class SelectMenu extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.selectMenuRecyclerView) RecyclerView menuRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_menu, container, false);
        unbinder = ButterKnife.bind(this, v);

        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        getActivity().setTitle("Select Menu");

        final CurateAPI api = CurateConnection.setUpRetrofit();
        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        Integer restaurantID = prefs.getInt("restaurantID", -1);
        Call<ArrayList<CurateMenu>> call = api.getMenusForRestaurant(String.valueOf(restaurantID));

        call.enqueue(new Callback<ArrayList<CurateMenu>>() {
            @Override
            public void onResponse(Call<ArrayList<CurateMenu>> call, Response<ArrayList<CurateMenu>> response) {
                if (response.body() != null) {
                    menuRecyclerView.setAdapter(new SelectMenuAdapter(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CurateMenu>> call, Throwable t) {
                Log.d("FAILURE", "Message " + t.getMessage());
            }
        });


        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
