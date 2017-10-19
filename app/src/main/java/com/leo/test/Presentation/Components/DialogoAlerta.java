package com.leo.test.Presentation.Components;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.leo.test.R;


public class DialogoAlerta extends DialogFragment {
    private IOnAlertDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage(getString(R.string.alert))
                .setTitle(getString(R.string.whitout_conexion))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        if(listener != null)
                            listener.onDialogOKClik();
                    }
                });

        return builder.create();
    }


    public void setListener(IOnAlertDialogListener listener) {
        this.listener = listener;
    }
    public interface IOnAlertDialogListener{
        void onDialogOKClik();
    }
}
