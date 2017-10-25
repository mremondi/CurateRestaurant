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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.MenuItem;
import curatetechnologies.com.curate.models.Order;
import curatetechnologies.com.curate.network.FirebaseAPI;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mremondi on 10/2/17.
 */

public class CurrentOrderDetails extends Fragment {

    FirebaseRecyclerAdapter itemRowAdapter;

    Order order;
    DatabaseReference orderRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_order_details, container, false);

        configureView(v);
        configureFirebase(v);
        return v;
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public void setOrderRef(DatabaseReference orderRef){
        this.orderRef = orderRef;
    }

    private void configureFirebase(View v) {
        RecyclerView orderQueue = (RecyclerView) v.findViewById(R.id.order_details_recyclerview);
        orderQueue.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        DatabaseReference ref = FirebaseAPI.SHARED.getOrderItemsRef(orderRef);

        itemRowAdapter = new FirebaseRecyclerAdapter<MenuItem, OrderDetailsViewHolder>(
                MenuItem.class,
                R.layout.order_details_item_row,
                OrderDetailsViewHolder.class,
                ref) {
            @Override
            public void populateViewHolder(OrderDetailsViewHolder holder, MenuItem item, int position) {
                holder.setItemName(item.getItemName());
            }

            @Override
            public OrderDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                OrderDetailsViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new OrderQueueViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                        Fragment orderDetails = new NewOrderDetails();
//                        FragmentManager fm = getFragmentManager();
//                        FragmentTransaction transaction = fm.beginTransaction();
//                        transaction.replace(R.id.content_frame, orderDetails);
//                        transaction.commit();
                    }
                });
                return viewHolder;
            }


        };

        orderQueue.setAdapter(itemRowAdapter);
    }

    private void configureView(View v) {
        getActivity().setTitle("Order Details");

        ImageView profilePicture = (ImageView) v.findViewById(R.id.order_details_profile_picture);
        Glide.with(v)
                .load(order.getProfilePictureURL())
                .into(profilePicture);

        TextView fullName = (TextView) v.findViewById(R.id.order_details_full_name);
        fullName.setText(order.getFullName());

        TextView orderUserName = (TextView) v.findViewById(R.id.order_details_username);
        TextView orderTotalPrice = (TextView) v.findViewById(R.id.order_details_total_price);
        orderUserName.setText(this.order.getUsername());
        orderTotalPrice.setText("$" + this.order.getPrice());

        Button btnCompleteOrder = (Button) v.findViewById(R.id.btn_complete_order);
        final String restaurantID;
        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        restaurantID = prefs.getString("restaurantID", "");//"No name defined" is the default value.
        btnCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setCompletedTime("" + System.currentTimeMillis());
                FirebaseAPI.SHARED.moveCurrentOrderToCompletedOrders(restaurantID, orderRef, order);
                FirebaseAPI.SHARED.saveOrderHistory(order);

                CurrentOrderQueue currentOrderQueue = new CurrentOrderQueue();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content_frame, currentOrderQueue);
                transaction.commit();
            }
        });
    }
}
