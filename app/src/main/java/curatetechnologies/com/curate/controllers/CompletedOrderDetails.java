package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class CompletedOrderDetails extends Fragment {

        FirebaseRecyclerAdapter itemRowAdapter;

        Order order;
        DatabaseReference orderRef;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
        savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_completed_order_details, container, false);

        configureView(v);
        configureFirebase(v);
        return v;
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

        TextView instructions = v.findViewById(R.id.order_details_instructions);
        instructions.setText(order.getInstructions());

        TextView orderUserName = (TextView) v.findViewById(R.id.order_details_username);
        TextView orderTotalPrice = (TextView) v.findViewById(R.id.order_details_total_price);
        orderUserName.setText(this.order.getUsername());
        orderTotalPrice.setText("$" + this.order.getPrice());
    }

    public void setOrder(Order order){
        this.order = order;
    }

    public void setOrderRef(DatabaseReference orderRef){
        this.orderRef = orderRef;
    }
}
