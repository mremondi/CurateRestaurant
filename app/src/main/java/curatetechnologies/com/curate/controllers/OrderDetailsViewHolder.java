package curatetechnologies.com.curate.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 10/1/17.
 */

public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

    private final TextView itemName;
    private final TextView itemPrice;

    public OrderDetailsViewHolder(final View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.order_details_row_item_name);
        itemPrice = (TextView) itemView.findViewById(R.id.order_details_item_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
    }

    private OrderQueueViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnClickListener(OrderQueueViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setItemName(String itemName) {
        this.itemName.setText(itemName);
    }

    public void setItemPrice(String itemPrice){
        this.itemPrice.setText(itemPrice);
    }

}
