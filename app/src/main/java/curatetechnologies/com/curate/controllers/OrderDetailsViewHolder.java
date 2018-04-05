package curatetechnologies.com.curate.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import curatetechnologies.com.curate.OrderQueueViewHolder;
import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 10/1/17.
 */

public class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.order_details_row_item_name) TextView itemName;
    @BindView(R.id.order_details_item_price) TextView itemPrice;

    public OrderDetailsViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemName(String itemName) {
        this.itemName.setText(itemName);
    }

    public void setItemPrice(String itemPrice){
        this.itemPrice.setText(itemPrice);
    }

}
