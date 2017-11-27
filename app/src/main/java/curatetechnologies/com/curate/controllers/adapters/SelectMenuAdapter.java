package curatetechnologies.com.curate.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.SelectMenu;
import curatetechnologies.com.curate.models.Menu;
import retrofit2.Call;

/**
 * Created by mremondi on 11/27/17.
 */

public class SelectMenuAdapter extends RecyclerView.Adapter<SelectMenuAdapter.ViewHolder> {
    ArrayList<Menu> menus;

    public SelectMenuAdapter(ArrayList<Menu> menus){
        this.menus = menus;
    }

    @Override
    public SelectMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.select_menu_row, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(SelectMenuAdapter.ViewHolder holder, int position) {
        holder.tvMenuName.setText(menus.get(position).getMenuName());
    }

    @Override
    public int getItemCount() {
        return this.menus.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView tvMenuName;

        public ViewHolder(View menuRowView) {
            super(menuRowView);
            tvMenuName = menuRowView.findViewById(R.id.select_menu_menu_name);
        }
    }
}