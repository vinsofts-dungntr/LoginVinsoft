package com.login.tridung.loginvinsofts.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public static void showToast(Context context,String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
}
