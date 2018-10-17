package com.login.tridung.loginvinsofts.model;

public class CountryModel {
    private String nameEn;
    private String nameTrans;
    private int image;

    public CountryModel(String nameEn, String nameTrans, int image) {
        this.nameEn = nameEn;
        this.nameTrans = nameTrans;
        this.image = image;
    }


    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameTrans() {
        return nameTrans;
    }

    public void setNameTrans(String nameTrans) {
        this.nameTrans = nameTrans;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
