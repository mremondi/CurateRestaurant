package curatetechnologies.com.curate.controllers;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;
import curatetechnologies.com.curate.controllers.dialogs.AcceptOrderDialog;
import curatetechnologies.com.curate.models.Firebase.FirebaseMenuItem;
import curatetechnologies.com.curate.models.Firebase.FirebaseOrder;
import curatetechnologies.com.curate.network.FirebaseAPI;

import static android.content.Context.MODE_PRIVATE;

public class NewOrderDetails extends Fragment implements AcceptOrderDialog.AcceptOrderDialogListener{

    private FirebaseOrder firebaseOrder;

    DatabaseReference orderRef;
    FirebaseRecyclerAdapter itemRowAdapter;

    Unbinder unbinder;

    @BindView(R.id.order_details_recyclerview) RecyclerView orderQueue;
    @BindView(R.id.order_details_profile_picture) ImageView profilePicture;
    @BindView(R.id.order_details_full_name)TextView fullName;
    @BindView(R.id.order_details_instructions)TextView instructions;
    @BindView(R.id.order_details_username)TextView orderUserName;
    @BindView(R.id.order_details_total_price) TextView orderTotalPrice;
    @BindView(R.id.order_details_accept_button) Button btnAccept;
    @BindView(R.id.order_details_reject_button) Button btnReject;

    View v;
    AcceptOrderDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_new_order_details, container, false);
        unbinder = ButterKnife.bind(this, v);
        configureView(v);

        configureFirebase(v);
        return v;
    }

    private void configureFirebase(View v) {
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
                holder.setItemPrice(item.getItemPrice().toString());
            }

            @Override
            public OrderDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                OrderDetailsViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
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

        final AcceptOrderDialog.AcceptOrderDialogListener self = this;

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AcceptOrderDialog();
                dialog.setListener(self);
                dialog.show(getFragmentManager(), "Accept FirebaseOrder");
            }
        });

        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Rejecting FirebaseOrder");
                alertDialog.setMessage("Are you sure you want to reject this firebaseOrder?");

                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAPI.SHARED.rejectNewOrder(orderRef, firebaseOrder);
                                NewOrderQueue newOrderQueue = new NewOrderQueue();

                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.replace(R.id.content_frame, newOrderQueue);
                                transaction.commit();
                            }
                        });
                alertDialog.show();


            }
        });
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setOrderRef(DatabaseReference ref){
        this.orderRef = ref;
    }

    public void setFirebaseOrder(FirebaseOrder firebaseOrder) { this.firebaseOrder = firebaseOrder;}

    @Override
    public void onPositiveClick(String waitTime) {
        firebaseOrder.setTimeToCompletion(waitTime);
        String restaurantID;
        SharedPreferences prefs = getActivity().getSharedPreferences("RESTAURANT_PREFS", MODE_PRIVATE);
        restaurantID = prefs.getString("restaurantID", "");//"No name defined" is the default value.
        FirebaseAPI.SHARED.moveNewOrderToCurrentOrder(restaurantID, orderRef, firebaseOrder);
        Toast.makeText(this.getActivity(), "Wait time set: " + waitTime, Toast.LENGTH_LONG).show();
        dialog.dismiss();

        NewOrderQueue newOrderQueue = new NewOrderQueue();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content_frame, newOrderQueue);
        transaction.commit();

    }

    @Override
    public void onCancelClick() {
        Toast.makeText(this.getActivity(), "No wait time set", Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
