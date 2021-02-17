package com.hiren.addtocart.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hiren.addtocart.MainActivity;
import com.hiren.addtocart.R;
import com.hiren.addtocart.roomdatabase.Cart;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ViewHolder> {
    private List<Cart>carts;
    private Context context;

    public CartProductAdapter(List<Cart> carts, Context context) {
        this.carts = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
              final Cart cart=carts.get(position);
if(carts.get(position).imageid==null) {
}
else{
    holder.primage.setImageURI(Uri.parse(carts.get(position).imageid));

}

        holder.prquan.setText(" "+carts.get(position).getQuantity());

        holder.prspec.setText(carts.get(position).getSpec());
        holder.prname.setText(carts.get(position).getName());
        holder.prprice.setText("\u20A6" +carts.get(position).getPrice());

        holder.deletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carts.remove(position);
                notifyDataSetChanged();
                MainActivity.myDatabase.cartDao().deleteItem(cart.getId());

                int cartcount= MainActivity.myDatabase.cartDao().countCart();
                Intent intent=new Intent("mymsg");
                intent.putExtra("cartcount",cartcount);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

            }
        });


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carts.get(position).setQuantity(carts.get(position).getQuantity() + 1);
                holder.prquan.setText(" "+carts.get(position).getQuantity());
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carts.get(position).setQuantity(carts.get(position).getQuantity() - 1);
               holder.prquan.setText(" "+carts.get(position).getQuantity());

            }
        });


        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.edit.getVisibility()==View.VISIBLE){
                    holder.edit.setVisibility(View.GONE);
                }
               else if(holder.edit.getVisibility()==View.GONE){
                    holder.edit.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
private ImageView primage,deletbtn;
private ImageView add,minus;
private TextView prprice, prspec, prquan, prname;
private RelativeLayout edit;
private LinearLayout click;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            primage=(ImageView)itemView.findViewById(R.id.primage);
            deletbtn=(ImageView)itemView.findViewById(R.id.deletbtn);
            prprice=(TextView)itemView.findViewById(R.id.txtprprice);
            prspec=(TextView)itemView.findViewById(R.id.spec);
            prquan=(TextView)itemView.findViewById(R.id.quan);
            add=(ImageView)itemView.findViewById(R.id.add);
            minus=(ImageView) itemView.findViewById(R.id.minus);
            edit=(RelativeLayout) itemView.findViewById(R.id.edit);
            click=(LinearLayout) itemView.findViewById(R.id.click);
            prname=(TextView) itemView.findViewById(R.id.name);



        }
    }
}
