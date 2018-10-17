package com.login.tridung.loginvinsofts.model;

public class AddItemModel {
    private String mNameEn;
    private String mNameTrans;
    private int mImage;

    public AddItemModel(String mNameEn, String mNameTrans, int mImage) {
        this.mNameEn = mNameEn;
        this.mNameTrans = mNameTrans;
        this.mImage = mImage;
    }

    public String getmNameEn() {
        return mNameEn;
    }

    public void setmNameEn(String mNameEn) {
        this.mNameEn = mNameEn;
    }

    public String getmNameTrans() {
        return mNameTrans;
    }

    public void setmNameTrans(String mNameTrans) {
        this.mNameTrans = mNameTrans;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }
}
