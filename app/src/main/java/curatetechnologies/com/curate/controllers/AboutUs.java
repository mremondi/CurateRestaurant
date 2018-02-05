package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 10/29/17.
 */

public class AboutUs extends Fragment {

    private Unbinder unbinder;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about_us, container, false);

        getActivity().setTitle("About Us");

        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnPrivacyPolicy) void openPrivacyPolicy(){
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://curatemeals.com/privacypolicy"));
            getActivity().startActivity(browserIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this.getActivity(), "No application can handle this request. Please install a webbrowser", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btnTermsOfService) void termsOfService(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.curatemeals.com/termsofservice"));
        getActivity().startActivity(browserIntent);
    }
}