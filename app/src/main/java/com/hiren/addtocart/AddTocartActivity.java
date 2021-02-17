package com.hiren.addtocart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hiren.addtocart.roomdatabase.Cart;

public class AddTocartActivity extends AppCompatActivity {
    private ImageView primage, plus, minus, back;
    private TextView price, quantity, pname, spec, cartcount;
   RelativeLayout addtocart;
   LinearLayout cart;
   int quan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tocart);

        primage=(ImageView)findViewById(R.id.primage);
        price=(TextView)findViewById(R.id.txtprprice);
        cartcount=(TextView)findViewById(R.id.txtcartcount);
        quantity=(TextView)findViewById(R.id.quan);
        pname=(TextView)findViewById(R.id.product_name);
        spec=(TextView)findViewById(R.id.spec);
        plus=(ImageView)findViewById(R.id.add);
        minus=(ImageView)findViewById(R.id.minus);
        back=(ImageView)findViewById(R.id.bac);
        cart=(LinearLayout)findViewById(R.id.cart);

        int cartcountval= MainActivity.myDatabase.cartDao().countCart();


        Intent intent=getIntent();
        final Uri imageurl=intent.getParcelableExtra("imageurl");
        final String prname=intent.getStringExtra("prname");
        final String prprice=intent.getStringExtra("prprice");
        final String prspec=intent.getStringExtra("prspec");
        final int id=intent.getIntExtra("id",0);
        quan=intent.getIntExtra("quantity",1);

        primage.setImageURI(imageurl);
        price.setText(prprice);
        spec.setText(prspec);
        pname.setText(prname);
        price.setText("\u20A6" +prprice);
        quantity.setText(quan + "");
        cartcount.setText(cartcountval + "");


        addtocart=(RelativeLayout) findViewById(R.id.addtocartbtn);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart=new Cart();
                cart.setId(id);
                cart.setImageid(imageurl.toString());
                cart.setName(prname);
                cart.setPrice(prprice);
                cart.setSpec(prspec);
                cart.setQuantity(quan);

               if (MainActivity.myDatabase.cartDao().isAddToCart(id)!=1){
                   MainActivity.myDatabase.cartDao().addToCart(cart);

                CustomDoneLayout(AddTocartActivity.this, prname);


               }else {
                   Toast.makeText(AddTocartActivity.this, "You are Already added to cart!", Toast.LENGTH_SHORT).show();

               }
            }
        });



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              quan=quan+1;
                quantity.setText(quan + "");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quan=quan-1;
                quantity.setText(quan + "");

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent car=new Intent(getApplicationContext(), MyCartActivity.class);
               startActivity(car);
               finish();


            }
        });
    }



    public void CustomDoneLayout(Context con, String item) {
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddTocartActivity.this);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setIcon(R.drawable.done);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialog = inflater.inflate(R.layout.custom, null);

            if (Build.VERSION.SDK_INT >= 21) {
               alertDialogBuilder.setView(dialog);

                ((TextView)dialog.findViewById(R.id.itemname)).setText(item +" has been added to your bag");

                ((Button)dialog.findViewById(R.id.ve)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in=new Intent(getApplicationContext(), MyCartActivity.class);
                        startActivity(in);
                        finish();
                    }
                });

                ((Button)dialog.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                        finish();
                    }
                });
            }

            else {
                alertDialogBuilder.setMessage(item + "has been added to your bag");
                alertDialogBuilder.setTitle("EXIT");


                alertDialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        arg0.dismiss();
                        finish();
                    }
                });
                alertDialogBuilder.setNegativeButton("Open Bag", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();

                        Intent in=new Intent(getApplicationContext(), MyCartActivity.class);
                        startActivity(in);
                        finish();
                    }
                });

            }
            alertDialogBuilder.create().show();
        } catch (Exception e) {
        }
    }


}
