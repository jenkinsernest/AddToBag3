package com.hiren.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hiren.addtocart.adapter.CartProductAdapter;
import com.hiren.addtocart.roomdatabase.Cart;

import java.util.List;

public class MyCartActivity extends AppCompatActivity {
    RecyclerView rv;
    CartProductAdapter cartProductAdapter;
    List<Cart>carts;
    TextView tvcount, total;
    ImageView bac;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        rv=(RecyclerView)findViewById(R.id.res);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        total=(TextView)findViewById(R.id.total);
        bac=(ImageView) findViewById(R.id.bac);
        tvcount=(TextView)findViewById(R.id.txtcartcount);

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getCartData();


        int count=cartProductAdapter.getItemCount();
        MainActivity.totalprice=0;

                updatacartcount();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMasage,new IntentFilter("mymsg"));

    }

    private void updatacartcount() {
        int count=cartProductAdapter.getItemCount();


        if (count==0){
            tvcount.setText("Your Cart is Empty");
        }else {
            tvcount.setText(String.valueOf(count));
        }



    }

    private void getCartData() {
        carts=MainActivity.myDatabase.cartDao().getData();
        cartProductAdapter=new CartProductAdapter(carts,this);
        rv.setAdapter(cartProductAdapter);
countData();
    }


    void countData(){

        for(int a=0; a<= carts.size()-1; a++){
            int c=Integer.parseInt(  carts.get(a).getPrice());
            MainActivity.totalprice+=c;

        }
        total.setText("\u20A6" + MainActivity.totalprice);

    }
    @Override
    protected void onResume() {
        super.onResume();
        updatacartcount();
    }

    public BroadcastReceiver mMasage=new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String cartcount=intent.getStringExtra("cartcount");
            if (carts.size()  ==0){
                tvcount.setText("Your Cart is Empty");
            }else {
                tvcount.setText(String.valueOf(carts.size()));
            }
            countData();

        }
    };
}
