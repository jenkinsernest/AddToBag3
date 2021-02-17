package com.hiren.addtocart.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hiren.addtocart.AddTocartActivity;
import com.hiren.addtocart.R;
import com.hiren.addtocart.model.MyProductData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MySearchAdapter extends RecyclerView.Adapter<MySearchAdapter.ViewHolder> {
    private List<MyProductData>myProductData;
    private Context context;

    public MySearchAdapter(List<MyProductData> myProductData, Context context) {

        this.myProductData = myProductData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        //Glide.with(context).load(myProductData.get(position).primage).into(
        holder.prname.setText(myProductData.get(position).getPrname());
        holder.prd_name_sub.setText(myProductData.get(position).getPr_sub());
        holder.prod_spec.setText(myProductData.get(position).getPr_spec());
        holder.prname.setText(myProductData.get(position).getPrname());
       holder.primage.setImageURI(myProductData.get(position).primage);
        holder.prprice.setText("\u20A6" + myProductData.get(position).prprice);

        holder.primage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, AddTocartActivity.class);
                Uri imageurl=myProductData.get(position).primage;
                String prprice=myProductData.get(position).prprice;
                intent.putExtra("imageurl",imageurl);
                intent.putExtra("prname",myProductData.get(position).prname);
                intent.putExtra("prprice",myProductData.get(position).prprice);
                intent.putExtra("id",myProductData.get(position).id);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myProductData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
private ImageView primage;
private TextView prprice, prd_name_sub,prod_spec, prname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            primage=(ImageView)itemView.findViewById(R.id.primage);
            prprice=(TextView)itemView.findViewById(R.id.txtprprice);
            prname=(TextView)itemView.findViewById(R.id.product_name);
            prd_name_sub=(TextView)itemView.findViewById(R.id.product_name_sub);
            prod_spec=(TextView)itemView.findViewById(R.id.product_spec);
        }
    }
}
