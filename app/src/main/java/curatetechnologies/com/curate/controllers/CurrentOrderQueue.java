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

import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.Order;
import curatetechnologies.com.curate.network.FirebaseAPI;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mremondi on 10/1/17.
 */

public class CurrentOrderQueue extends Fragment {

    FirebaseRecyclerAdapter orderQueueAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_order_queue, container, false);
        RecyclerView orderQueue = (RecyclerView) v.findViewById(R.id.orderQueueRecyclerView);
        orderQueue.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        getActivity().setTitle("Current Orders");

        String restaurantID;
        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        restaurantID = prefs.getString("restaurantID", "");//"No name defined" is the default value.

        final DatabaseReference ref = FirebaseAPI.SHARED.getCurrentOrdersRef(restaurantID);

        orderQueueAdapter = new FirebaseRecyclerAdapter<Order, OrderQueueViewHolder>(
                Order.class,
                R.layout.order_row,
                OrderQueueViewHolder.class,
                ref) {

            @Override
            public void populateViewHolder(OrderQueueViewHolder holder, Order order, int position) {
                holder.setUserName(order.getUsername());
                holder.setOrderPrice(order.getPrice());
            }

            @Override
            public OrderQueueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                OrderQueueViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new OrderQueueViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CurrentOrderDetails currentOrderDetails = new CurrentOrderDetails();

                        currentOrderDetails.setOrderRef(orderQueueAdapter.getRef(position));
                        currentOrderDetails.setOrder((Order) orderQueueAdapter.getItem(position));

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.content_frame, currentOrderDetails);
                        transaction.commit();
                    }
                });
                return viewHolder;
            }


        };

        orderQueue.setAdapter(orderQueueAdapter);
        return v;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        orderQueueAdapter.cleanup();
    }
}
