package curatetechnologies.com.curate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mremondi on 9/15/17.
 */

public class OrderQueueViewHolder extends RecyclerView.ViewHolder {

    private final TextView itemName;
    private final TextView userName;
    private final TextView itemPrice;
    private final Button btnAcceptOrder;

    public OrderQueueViewHolder(View itemView) {
        super(itemView);
        itemName = (TextView) itemView.findViewById(R.id.order_row_item_name);
        userName = (TextView) itemView.findViewById(R.id.order_row_user_name);
        itemPrice = (TextView) itemView.findViewById(R.id.order_row_item_price);
        btnAcceptOrder = (Button) itemView.findViewById(R.id.order_row_accept_button);
    }

    public void setItemName(String name) {
        this.itemName.setText(name);
    }

    public void setUserName(String userName){
        this.userName.setText(userName);
    }

    public void setItemPrice(String price){
        this.itemPrice.setText(price);
    }

    // TODO: hook up button to firebase
}
