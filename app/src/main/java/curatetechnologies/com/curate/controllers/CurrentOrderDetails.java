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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.models.Curate.CurateMenuItem;
import curatetechnologies.com.curate.models.Firebase.FirebaseMenuItem;
import curatetechnologies.com.curate.models.Firebase.FirebaseOrder;
import curatetechnologies.com.curate.network.FirebaseAPI;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mremondi on 10/2/17.
 */

public class CurrentOrderDetails extends Fragment {

    FirebaseRecyclerAdapter itemRowAdapter;

    FirebaseOrder firebaseOrder;
    DatabaseReference orderRef;

    Unbinder unbinder;

    @BindView(R.id.order_details_profile_picture) ImageView profilePicture;
    @BindView(R.id.order_details_full_name) TextView fullName;
    @BindView(R.id.order_details_instructions) TextView instructions;
    @BindView(R.id.order_details_username) TextView orderUserName;
    @BindView(R.id.order_details_total_price) TextView orderTotalPrice;
    @BindView(R.id.btn_complete_order) Button btnCompleteOrder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_current_order_details, container, false);
        unbinder = ButterKnife.bind(this, v);
        configureView(v);
        configureFirebase(v);
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setFirebaseOrder(FirebaseOrder firebaseOrder){
        this.firebaseOrder = firebaseOrder;
    }

    public void setOrderRef(DatabaseReference orderRef){
        this.orderRef = orderRef;
    }

    private void configureFirebase(View v) {
        RecyclerView orderQueue = (RecyclerView) v.findViewById(R.id.order_details_recyclerview);
        orderQueue.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        DatabaseReference ref = FirebaseAPI.SHARED.getOrderItemsRef(orderRef);

        itemRowAdapter = new FirebaseRecyclerAdapter<FirebaseMenuItem, OrderDetailsViewHolder>(
                FirebaseMenuItem.class,
                R.layout.order_details_item_row,
                OrderDetailsViewHolder.class,
                ref) {
            @Override
            public void populateViewHolder(OrderDetailsViewHolder holder, FirebaseMenuItem item, int position) {
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
        getActivity().setTitle("FirebaseOrder Details");

        Glide.with(v)
                .load(firebaseOrder.getProfilePictureURL())
                .into(profilePicture);

        fullName.setText(firebaseOrder.getFullName());

        instructions.setText(firebaseOrder.getInstructions());

        orderUserName.setText(this.firebaseOrder.getUsername());
        orderTotalPrice.setText("$" + this.firebaseOrder.getPrice());

        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        final String restaurantID = prefs.getString("restaurantID", "");//"No name defined" is the default value

        btnCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseOrder.setCompletedTime("" + System.currentTimeMillis());
                FirebaseAPI.SHARED.moveCurrentOrderToCompletedOrders(restaurantID, orderRef, firebaseOrder);
                FirebaseAPI.SHARED.saveOrderHistory(orderRef, firebaseOrder);

                CurrentOrderQueue currentOrderQueue = new CurrentOrderQueue();

                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content_frame, currentOrderQueue);
                transaction.commit();
            }
        });
    }
}
