package com.login.tridung.loginvinsofts.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.login.tridung.loginvinsofts.R;
import com.login.tridung.loginvinsofts.model.CountryModel;

import java.util.ArrayList;
import java.util.List;


public class DialogAddItem {


    public static void showDialog(Context context){
        final AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_dialog,null);
        builder.setView(view);
        builder.setTitle("Điền thông tin");
        final EditText edNameEns=view.findViewById(R.id.edNameEns);
        final EditText edNameTrans=view.findViewById(R.id.edNameTrans);
        Button btChoseIm=view.findViewById(R.id.btChoseIm);

        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<CountryModel> mList;
                String edNameTran=edNameTrans.getText().toString().trim();
                String edNameEn=edNameEns.getText().toString().trim();
                mList=new ArrayList<>();
            }
        });

        builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

}
