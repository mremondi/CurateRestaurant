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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.adapters.MenuSectionAdapter;
import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.models.Curate.CurateMenuSection;
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

    Unbinder unbinder;

    @BindView(R.id.manage_menu_recycler_view) RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manage_menu, container, false);
        unbinder = ButterKnife.bind(this, v);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final CurateAPI api = CurateConnection.setUpRetrofit();
        Call<CurateMenu[]> call = api.getMenuItemsBySection(menuId);
        call.enqueue(new Callback<CurateMenu[]>() {
            @Override
            public void onResponse(Call<CurateMenu[]> call, Response<CurateMenu[]> response) {
                CurateMenu menu = response.body()[0];
                getActivity().setTitle("Manage Menu: " + menu.getMenuName());
                if (response.body() != null) {
                    List<CurateMenuSection> sections =  menu.getCurateMenuSections();
                    if (sections != null) {
                        SectionedRecyclerViewAdapter sectionAdapter = new SectionedRecyclerViewAdapter();

                        for (CurateMenuSection section : sections) {
                            if (section.getItems().get(0).getItemName() != null) {
                                sectionAdapter.addSection(new MenuSectionAdapter(section, section.getItems()));
                            }
                        }
                        recyclerView.setAdapter(sectionAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CurateMenu[]> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setMenuId(int id){
        this.menuId = id;
    }
}
