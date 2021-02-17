package com.hiren.addtocart.cartapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyretroClinte {
    private static final String BASE_URL="https://jnjstore.in/jnjstoreapp/";
    private static MyretroClinte myClient;
    private Retrofit retrofit;

    private MyretroClinte(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized MyretroClinte getInstance(){
        if (myClient==null){
            myClient=new MyretroClinte();
        }
        return myClient;
    }
    public CartApi getMyApi(){
        return retrofit.create(CartApi.class);
    }

}
