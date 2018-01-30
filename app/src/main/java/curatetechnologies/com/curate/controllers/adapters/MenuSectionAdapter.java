package curatetechnologies.com.curate.controllers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.models.Curate.CurateMenuItem;
import curatetechnologies.com.curate.models.Curate.CurateMenuSection;
import curatetechnologies.com.curate.network.CurateAPI;
import curatetechnologies.com.curate.network.CurateConnection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mremondi on 11/28/17.
 */

public class MenuSectionAdapter extends StatelessSection {

    CurateMenuSection section;
    List<CurateMenuItem> sectionItems;

    public MenuSectionAdapter(CurateMenuSection section, List<CurateMenuItem> sectionItems){
        super(new SectionParameters.Builder(R.layout.section_item)
                .headerResourceId(R.layout.section_header)
                .build());
        this.section = section;
        this.sectionItems = sectionItems;

    }

    @Override
    public int getContentItemsTotal() {
        return sectionItems.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MenuSectionItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MenuSectionItemViewHolder itemHolder = (MenuSectionItemViewHolder) holder;

        // bind your view here
        itemHolder.item = this.sectionItems.get(position);
        itemHolder.itemName.setText(this.sectionItems.get(position).getItemName());
        if (itemHolder.item.getAvailable()){
            itemHolder.hideItemButton.setText("Hide");
            itemHolder.hideItemButton.setBackgroundColor(itemHolder.itemView.getResources().getColor(R.color.greenPastel));
        }
        else {
            itemHolder.hideItemButton.setText("Unhide");
            itemHolder.hideItemButton.setBackgroundColor(itemHolder.itemView.getResources().getColor(R.color.primaryRed));
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new SectionHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        SectionHeaderViewHolder headerHolder = (SectionHeaderViewHolder) holder;
        headerHolder.sectionName.setText(this.section.getSection());
    }

    protected class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.section_header_section_name) TextView sectionName;

        public SectionHeaderViewHolder(View headerView){
            super(headerView);
            ButterKnife.bind(this, headerView);
        }
    }

    protected class MenuSectionItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.section_item_item_name) TextView itemName;
        @BindView(R.id.section_item_hide_item_button) Button hideItemButton;

        CurateMenuItem item;

        @OnClick(R.id.section_item_hide_item_button) void onHideItemClick(Button btnHideItem){
            if (item.getAvailable()){
                btnHideItem.setText("Unhide");
                btnHideItem.setBackgroundColor(itemView.getResources().getColor(R.color.primaryRed));

                final CurateAPI api = CurateConnection.setUpRetrofit();
                // GET FIREBASE USER ID TOKEN
                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                mUser.getIdToken(true)
                        .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                if (task.isSuccessful()) {
                                    String idToken = task.getResult().getToken();
                                    Call<String> call = api.setItemAvailability(idToken, item.getItemID(), false);
                                    call.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            Log.d("RESPONSE", "onResponse: " + response.body());
                                            item.setAvailable(false);
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {
                                            Log.d("FAILURE", t.getMessage());
                                        }
                                    });
                                } else {
                                    // Handle error -> task.getException();
                                }
                            }
                        });
            }
            else {
                btnHideItem.setText("Hide");
                btnHideItem.setBackgroundColor(itemView.getResources().getColor(R.color.greenPastel));

                final CurateAPI api = CurateConnection.setUpRetrofit();


                FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                mUser.getIdToken(true)
                        .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                            public void onComplete(@NonNull Task<GetTokenResult> task) {
                                if (task.isSuccessful()) {
                                    String idToken = task.getResult().getToken();
                                    Call<String> call = api.setItemAvailability(idToken, item.getItemID(), true);
                                    call.enqueue(new Callback<String>() {
                                        @Override
                                        public void onResponse(Call<String> call, Response<String> response) {
                                            item.setAvailable(true);
                                        }

                                        @Override
                                        public void onFailure(Call<String> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    // Handle error -> task.getException();
                                }
                            }
                        });
            }
        }

        public MenuSectionItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}


