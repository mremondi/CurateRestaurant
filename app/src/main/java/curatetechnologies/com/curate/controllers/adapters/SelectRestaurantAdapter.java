package curatetechnologies.com.curate.controllers.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import curatetechnologies.com.curate.MainActivity;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.ChooseRestaurant;
import curatetechnologies.com.curate.controllers.ManageMenu;
import curatetechnologies.com.curate.models.Curate.CurateMenu;
import curatetechnologies.com.curate.models.Curate.CurateRestaurant;

/**
 * Created by mremondi on 2/10/18.
 */

public class SelectRestaurantAdapter extends RecyclerView.Adapter<SelectRestaurantAdapter.ViewHolder> {
        ChooseRestaurant activity;
        ArrayList<CurateRestaurant> curateRestaurants;

        public SelectRestaurantAdapter(ChooseRestaurant activity, ArrayList<CurateRestaurant> curateRestaurants){
            this.activity = activity;
            this.curateRestaurants = curateRestaurants;
        }

        @Override
        public SelectRestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            CardView view =  (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.select_restaurant_row_view, parent, false);
            return new SelectRestaurantAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SelectRestaurantAdapter.ViewHolder holder, final int position) {
            holder.tvRestaurantName.setText(curateRestaurants.get(position).getName());

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.saveRestaurantID(curateRestaurants.get(position).getID());
                }
            });
        }

        @Override
        public int getItemCount() {
            return this.curateRestaurants.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder  {
            @BindView(R.id.select_restaurant_row_restaurant_name)
            TextView tvRestaurantName;
            private CardView view;

            public ViewHolder(CardView restaurantRowView) {
                super(restaurantRowView);
                ButterKnife.bind(this, restaurantRowView);
                this.view = restaurantRowView;
            }
        }
    }
