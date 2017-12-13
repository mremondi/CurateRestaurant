package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 12/13/17.
 */

public class CloseShop extends Fragment {

    boolean isOpen = true;

    private Unbinder unbinder;

    @BindView(R.id.close_shop_btn) Button btnCloseShop;
    @OnClick(R.id.close_shop_btn) void openCloseShop(){
        if (isOpen){
            btnCloseShop.setText("Reopen");
            btnCloseShop.setBackgroundColor(getResources().getColor(R.color.greenPastel));
            this.isOpen = false;
            // TODO: Make call to database FORCE closing shop
        }
        else{
            btnCloseShop.setText("Close Up");
            btnCloseShop.setBackgroundColor(getResources().getColor(R.color.primaryRed));
            this.isOpen = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_close_shop, container, false);
        unbinder = ButterKnife.bind(this, v);


        return v;

    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}