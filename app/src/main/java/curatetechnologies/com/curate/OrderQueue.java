package curatetechnologies.com.curate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import curatetechnologies.com.curate.models.Order;


public class OrderQueue extends AppCompatActivity {

    FirebaseRecyclerAdapter orderQueueAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_queue);


        RecyclerView orderQueue = (RecyclerView) findViewById(R.id.orderQueueRecyclerView);
        orderQueue.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("order_queue");

        orderQueueAdapter = new FirebaseRecyclerAdapter<Order, OrderQueueViewHolder>(
                Order.class,
                R.layout.order_row,
                OrderQueueViewHolder.class,
                ref) {
            @Override
            public void populateViewHolder(OrderQueueViewHolder holder, Order order, int position) {
                holder.setItemName(order.getItemName());
            }
        };

        orderQueue.setAdapter(orderQueueAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderQueueAdapter.cleanup();
    }
}
