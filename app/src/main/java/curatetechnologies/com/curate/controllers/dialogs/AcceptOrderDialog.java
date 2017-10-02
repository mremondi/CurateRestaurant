package curatetechnologies.com.curate.controllers.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


import curatetechnologies.com.curate.R;

/**
 * Created by mremondi on 10/1/17.
 */

public class AcceptOrderDialog extends DialogFragment {

    public interface AcceptOrderDialogListener{
        public void onPositiveClick(String waitTime);
        public void onCancelClick();
    }

    private Spinner spinner;

    AcceptOrderDialogListener mListener;

    public void setListener(AcceptOrderDialogListener listener){
        this.mListener =  listener;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.accept_order_dialog, null);
        spinner = (Spinner) v.findViewById(R.id.wait_time_spinner);
        setUpButtonListeners(v);

        builder.setView(v);

        return builder.create();
    }

    private void setUpButtonListeners(View v){
        Button acceptWaitTimeButton = (Button) v.findViewById(R.id.btn_accept_wait_time);
        Button cancelWaitTimeButton = (Button) v.findViewById(R.id.btn_cancel_wait_time);
        acceptWaitTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onPositiveClick(spinner.getSelectedItem().toString());
            }
        });

        cancelWaitTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onCancelClick();
            }
        });
    }
}
