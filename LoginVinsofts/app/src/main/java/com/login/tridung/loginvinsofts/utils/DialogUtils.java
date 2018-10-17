package com.login.tridung.loginvinsofts.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {
    public static void showDialog(Context context,String tittle, String mess){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(tittle);
        builder.setMessage(mess);
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
