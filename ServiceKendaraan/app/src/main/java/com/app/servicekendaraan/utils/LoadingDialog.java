package com.app.servicekendaraan.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.app.servicekendaraan.R;


public class LoadingDialog extends AlertDialog {
    private Context context;
    private AlertDialog alertDialog;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public void startLoadingDialog() {
        Builder builder = new Builder(context);
        builder.setView(getLayoutInflater().inflate(R.layout.dialog_loading, null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }

   public void dismisDialog() {
        alertDialog.dismiss();
    }
}
