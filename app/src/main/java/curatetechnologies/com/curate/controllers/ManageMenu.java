package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.security.acl.LastOwnerException;

import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.adapters.MenuSectionAdapter;
import curatetechnologies.com.curate.models.Menu;
import curatetechnologies.com.curate.models.MenuSection;
import curatetechnologies.com.curate.models.Restaurant;
import curatetechnologies.com.curate.network.CurateAPI;
import curatetechnologies.com.curate.network.CurateConnection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mremondi on 10/2/17.
 */

public class ManageMenu extends Fragment{

    int menuId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_menu, container, false);

        // Todo: load all of the menu's items
        // Todo: allow the restaurant to hide specific items

        final RecyclerView recyclerView =  v.findViewById(R.id.manage_menu_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        final CurateAPI api = CurateConnection.setUpRetrofit();
        Call<Menu> call = api.getMenuById(menuId);
        call.enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {
                getActivity().setTitle("Manage Menu: " + response.body().getMenuName());
                Log.d("MENU LOADED", response.body().getMenuSections().get(0).getSection());

                SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

                for (MenuSection section: response.body().getMenuSections()) {
                    sectionAdapter.addSection(new MenuSectionAdapter(section,null));
                }

                recyclerView.setAdapter(sectionAdapter);
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
        return v;
    }

    public void setMenuId(int id){
        this.menuId = id;
    }
}
