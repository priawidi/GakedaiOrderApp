package com.mobcom.gakedaiorderapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.model.order_history.OrderHistoryModel;
import com.mobcom.gakedaiorderapp.ui.OrderActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder>  {

    List<OrderHistoryModel> mlistMenu;

    public HistoryListAdapter(List<OrderHistoryModel> listMenu){
        this.mlistMenu = listMenu;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_history_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        holder.OrderTgl.setText(mlistMenu.get(position).getOrder_time());

    }

    @Override
    public int getItemCount() {
        return mlistMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView OrderTgl;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            OrderTgl = itemView.findViewById(R.id.tv_tgl);
        }
    }
}
