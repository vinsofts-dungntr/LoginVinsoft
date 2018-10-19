package com.login.tridung.loginvinsofts.utils;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;
import com.login.tridung.loginvinsofts.R;

public class DialogShowIcon {
    public static void showIcon(Context context,int img){
        ImageView imShowIcon;
        Dialog dialog=new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.layout_dialog_image);
        imShowIcon=dialog.findViewById(R.id.imShowIcon);
        imShowIcon.setImageResource(img);
        dialog.show();
    }
}
