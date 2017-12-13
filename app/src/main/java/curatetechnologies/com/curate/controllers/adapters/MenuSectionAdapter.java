package curatetechnologies.com.curate.controllers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.MenuItem;
import curatetechnologies.com.curate.models.MenuSection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by mremondi on 11/28/17.
 */

public class MenuSectionAdapter extends StatelessSection {

    MenuSection section;
    List<MenuItem> sectionItems;

    public MenuSectionAdapter(MenuSection section, List<MenuItem> sectionItems){
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
        itemHolder.itemName.setText(this.sectionItems.get(position).getItemName());
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

    private class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView sectionName;

        public SectionHeaderViewHolder(View headerView){
            super(headerView);
            sectionName = headerView.findViewById(R.id.section_header_section_name);
        }
    }

    private class MenuSectionItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView itemName;
        private final Button btnHideItem;

        public MenuSectionItemViewHolder(final View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.section_item_item_name);
            btnHideItem = itemView.findViewById(R.id.section_item_hide_item_button);

            btnHideItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: make a call to the API that makes the menu item hidden
                    if (btnHideItem.getText().toString().equals("Hide")){
                        btnHideItem.setText("Unhide");
                        btnHideItem.setBackgroundColor(itemView.getResources().getColor(R.color.primaryRed));
                    }
                    else {
                        btnHideItem.setText("Hide");
                        btnHideItem.setBackgroundColor(itemView.getResources().getColor(R.color.greenPastel));
                    }
                }
            });
        }
    }
}


