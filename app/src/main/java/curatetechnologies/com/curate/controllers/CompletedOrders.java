package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.Firebase.FirebaseOrder;
import curatetechnologies.com.curate.network.FirebaseAPI;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mremondi on 10/2/17.
 */

public class CompletedOrders extends Fragment {

    FirebaseRecyclerAdapter orderQueueAdapter;
    Unbinder unbinder;

    @BindView(R.id.orderQueueRecyclerView) RecyclerView orderQueue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_completed_orders, container, false);
        unbinder = ButterKnife.bind(this, v);
        orderQueue.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        getActivity().setTitle("Completed Orders");

        Integer restaurantID;
        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        restaurantID = prefs.getInt("restaurantID", -1);//"No name defined" is the default value.

        final DatabaseReference ref = FirebaseAPI.SHARED.getCompletedOrdersRef(String.valueOf(restaurantID));

        orderQueueAdapter = new FirebaseRecyclerAdapter<FirebaseOrder, OrderQueueViewHolder>(
                FirebaseOrder.class,
                R.layout.order_row,
                OrderQueueViewHolder.class,
                ref) {

            @Override
            public void populateViewHolder(OrderQueueViewHolder holder, FirebaseOrder firebaseOrder, int position) {
                holder.setUserName(firebaseOrder.getUsername());
                holder.setOrderPrice(firebaseOrder.getPrice());
                holder.setProfilePicture(firebaseOrder.getProfilePictureURL());
            }

            @Override
            public OrderQueueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                OrderQueueViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new OrderQueueViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CompletedOrderDetails completedOrderDetails = new CompletedOrderDetails();

                        completedOrderDetails.setOrderRef(orderQueueAdapter.getRef(position));
                        completedOrderDetails.setFirebaseOrder((FirebaseOrder) orderQueueAdapter.getItem(position));

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.content_frame, completedOrderDetails);
                        transaction.commit();
                    }
                });
                return viewHolder;
            }


        };

        orderQueue.setAdapter(orderQueueAdapter);
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
