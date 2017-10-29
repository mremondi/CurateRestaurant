package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 10/29/17.
 */

public class Support extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_support, container, false);

        getActivity().setTitle("Support");
        // TODO: what settings are needed?

        return v;
    }
}