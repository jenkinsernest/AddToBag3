package com.hiren.addtocart.cartapi;

import com.hiren.addtocart.model.MyProductData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CartApi {

    @GET("getdata.php")
    Call<List<MyProductData>> getProductData();
}
