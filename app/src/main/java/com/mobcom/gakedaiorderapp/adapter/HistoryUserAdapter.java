package com.mobcom.gakedaiorderapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.model.order_history.OrderHistoryModel;
import com.mobcom.gakedaiorderapp.model.order_item.OrderItemModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HistoryUserAdapter extends RecyclerView.Adapter<HistoryUserAdapter.ViewHolder> {

    List<OrderItemModel> mlistMenu;

    public HistoryUserAdapter(List<OrderItemModel> listMenu){
        this.mlistMenu = listMenu;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_history_user, parent,false);
        HistoryUserAdapter.ViewHolder viewHolder = new HistoryUserAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_item_name.setText(mlistMenu.get(position).getItem_name());
        holder.tv_item_qty.setText(mlistMenu.get(position).getItem_qty());
        holder.tv_item_price.setText(mlistMenu.get(position).getItem_price());
        Picasso.get().load("https://admin.gakedai.com/api/order_item_image/"+mlistMenu.get(position).getItem_photo()).into(holder.iv_item_photo);

    }

    @Override
    public int getItemCount() {
        return mlistMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_name, tv_item_qty, tv_item_price;
        ImageView iv_item_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item_name = itemView.findViewById(R.id.tv_history_item_name);
            tv_item_qty = itemView.findViewById(R.id.tv_history_item_qty);
            tv_item_price = itemView.findViewById(R.id.tv_history_item_price);
            iv_item_photo = itemView.findViewById(R.id.iv_history_item_photo);
        }
    }
}
