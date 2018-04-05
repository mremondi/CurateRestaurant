package curatetechnologies.com.curate;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by mremondi on 9/15/17.
 */

public class OrderQueueViewHolder extends RecyclerView.ViewHolder {

    private final TextView userName;
    private final TextView orderPrice;
    private final ImageView profilePicture;


    public OrderQueueViewHolder(final View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.order_row_user_name);
        orderPrice = itemView.findViewById(R.id.order_row_price);
        profilePicture = itemView.findViewById(R.id.order_row_profile_picture);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

    }

    private OrderQueueViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
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

    public void setProfilePicture(String url) {
        if (url.equals("")) {
            profilePicture.setImageDrawable(itemView.getResources().getDrawable(R.drawable.pro_pic_holder));
        } else {
            Glide.with(itemView)
                    .load(url)
                    .into(profilePicture);
        }
    }

}
