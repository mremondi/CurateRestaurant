package curatetechnologies.com.curate.controllers;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 10/2/17.
 */

public class SelectMenu extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_select_menu, container, false);

        // Todo: load all of the restaurant's menus
        // Todo: allow the restaurant user to select which menu is active?

        return v;
    }

}
