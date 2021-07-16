package com.mobcom.gakedaiorderapp.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.model.cart.CartModel;
import com.mobcom.gakedaiorderapp.ui.CartFragment;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewholder> {

    private static final String TAG = "CartAdapter";
    int count = 0;
    List<CartModel> mCartList;
    public CartAdapter(List<CartModel> mCartList){
        this.mCartList=mCartList;

    }
    @NonNull
    @Override
    public CartViewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_cart_item, parent, false);
        CartViewholder viewHolder = new CartViewholder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartViewholder holder, int position) {

        holder.tv_name.setText(mCartList.get(position).getItemName());
        holder.tv_price.setText(mCartList.get(position).getItemPrice());
        holder.tv_qty.setText(mCartList.get(position).getItemQty());
        Picasso.get().load("https://admin.gakedai.com/api/image/" + mCartList.get(position).getItemPhoto()).into(holder.tv_photo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent mIntent = new Intent(v.getContext(), CartFragment.class);
                if(count<0){
                    //Toast.makeText(v.getContext(), "Gabisa mines bos", Toast.LENGTH_SHORT).show();
                }else{
                    Log.d(TAG, "onClick: Visible?");
                    holder.tv_qty.setText(String.valueOf(count));
                    //BTN PLUS
                    holder.btn_plus.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: Clicked?" + count);
                            count ++;
                            holder.tv_qty.setText(String.valueOf(count));

                        }
                    });
                    //BTN MINUS
                    holder.btn_min.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: Clicked?" +count);
                            if(count<=0){
                                //Toast.makeText(v.getContext(), "Gabisa mines bos", Toast.LENGTH_SHORT).show();
                            }else{
                                count --;
                                holder.tv_qty.setText(String.valueOf(count));
                                mIntent.putExtra("count",count);
                            }
                            holder.tv_qty.setText(String.valueOf(count));

                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return mCartList.size();
    }

    public class CartViewholder extends RecyclerView.ViewHolder {

        public TextView tv_name, tv_price, tv_qty;
        public ImageButton btn_plus, btn_min;
        public ImageView tv_photo;
        public CartViewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_photo = itemView.findViewById(R.id.tv_photo);

            tv_qty = itemView.findViewById(R.id.tv_btn_qty);
            btn_plus = itemView.findViewById(R.id.btn_plus);
            btn_min = itemView.findViewById(R.id.btn_min);
        }
    }
}
