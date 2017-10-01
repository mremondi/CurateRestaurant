package curatetechnologies.com.curate.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.MenuItem;
import curatetechnologies.com.curate.models.Order;
import curatetechnologies.com.curate.network.FirebaseAPI;

public class OrderDetails extends Fragment {

    private Order order;

    DatabaseReference orderRef;
    FirebaseRecyclerAdapter itemRowAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order_details, container, false);

        getActivity().setTitle("Order Details");

        TextView orderUserName = (TextView) v.findViewById(R.id.order_details_username);
        TextView orderTotalPrice = (TextView) v.findViewById(R.id.order_details_total_price);
        orderUserName.setText(this.order.getUsername());
        orderTotalPrice.setText(this.order.getPrice());

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
//                        Fragment orderDetails = new OrderDetails();
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
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void setOrderRef(DatabaseReference ref){
        this.orderRef = ref;
    }

    public void setOrder(Order order) { this.order = order; }
}
