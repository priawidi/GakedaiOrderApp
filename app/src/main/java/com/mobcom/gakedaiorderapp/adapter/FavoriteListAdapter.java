package com.mobcom.gakedaiorderapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.model.cart.CartModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.ViewHolder> {

    List<CartModel> mlistMenu;

    public FavoriteListAdapter(List<CartModel> listMenu) {
        this.mlistMenu = listMenu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_favorite_item, parent, false);
        FavoriteListAdapter.ViewHolder viewHolder = new FavoriteListAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_item_name.setText(mlistMenu.get(position).getItemName());
        holder.tv_item_price.setText("Rp." + mlistMenu.get(position).getItemPrice());

        Picasso.get().load("https://admin.gakedai.com/api/image/" + mlistMenu.get(position).getItemPhoto()).into(holder.iv_item_photo);


    }

    @Override
    public int getItemCount() {
        return mlistMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_name, tv_item_price;
        ImageView iv_item_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_name = itemView.findViewById(R.id.tv_favorite_item_name);
            tv_item_price = itemView.findViewById(R.id.tv_favorite_item_price);
            iv_item_photo = itemView.findViewById(R.id.iv_favorite_item_photo);
        }
    }
}
