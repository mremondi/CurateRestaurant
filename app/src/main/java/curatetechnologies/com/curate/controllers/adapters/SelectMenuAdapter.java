package curatetechnologies.com.curate.controllers.adapters;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.ManageMenu;
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
        CardView view =  (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.select_menu_row, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(SelectMenuAdapter.ViewHolder holder, final int position) {
        holder.tvMenuName.setText(menus.get(position).getMenuName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageMenu manageMenu = new ManageMenu();
                manageMenu.setMenuId(menus.get(position).getMenuID());

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                FragmentManager fm = activity.getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content_frame, manageMenu);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.menus.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.select_menu_menu_name) TextView tvMenuName;
        private CardView view;

        public ViewHolder(CardView menuRowView) {
            super(menuRowView);
            ButterKnife.bind(this, menuRowView);
            this.view = menuRowView;
        }
    }
}
