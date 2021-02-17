package com.hiren.addtocart.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class MyProductData {
    @SerializedName("id")
    public int id;

    public String prname;
    public String pr_sub;
    public String pr_spec;
    public int pr_quantity;

    @SerializedName("primage")
    public Uri primage;

    @SerializedName("prprice")
    public String prprice;

    public MyProductData(int id, String prname, Uri primage, String prprice, String pr_sub, String pr_spec, int quan) {
        this.id = id;
        this.prname = prname;
        this.primage = primage;
        this.prprice = prprice;
        this.pr_sub = pr_sub;
        this.pr_spec = pr_spec;
        this.pr_quantity= quan;
    }

    public int getId() {
        return id;
    }


    public int getPr_quantity() {
        return pr_quantity;
    }

    public void setPr_quantity(int quan){
        this.pr_quantity=quan;
    }

    public String getPrname() {
        return prname;
    }

    public Uri getPrimage() {
        return primage;
    }

    public String getPrprice() {
        return prprice;
    }
    public String getPr_sub() {
    return pr_sub;
    }
    public String getPr_spec() {
        return pr_spec;
    }
}
