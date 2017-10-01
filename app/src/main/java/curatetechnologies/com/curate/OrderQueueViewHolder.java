package curatetechnologies.com.curate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mremondi on 9/15/17.
 */

public class OrderQueueViewHolder extends RecyclerView.ViewHolder {

    private final TextView userName;
    private final TextView orderPrice;


    public OrderQueueViewHolder(final View itemView) {
        super(itemView);
        userName = (TextView) itemView.findViewById(R.id.order_row_user_name);
        orderPrice = (TextView) itemView.findViewById(R.id.order_row_price);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }

    private OrderQueueViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        public void onItemClick(View view, int position);
    }

    public void setOnClickListener(OrderQueueViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setUserName(String userName){
        this.userName.setText(userName);
    }

    public void setOrderPrice(String price){
        this.orderPrice.setText("$" + price);
    }

}
