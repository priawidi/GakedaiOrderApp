package com.mobcom.gakedaiorderapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.model.MenuModel;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MenuModel> mlistMenu;

    public MenuAdapter(List<MenuModel> listMenu){
        this.mlistMenu = listMenu;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_id, tv_name, tv_code, tv_price, tv_photo, tv_type, tv_detail, tv_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_code = itemView.findViewById(R.id.tv_code);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_photo = itemView.findViewById(R.id.tv_photo);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_detail = itemView.findViewById(R.id.tv_detail);
            tv_status = itemView.findViewById(R.id.tv_status);
        }

        public TextView getTv_name() { return tv_name;}

        public TextView getTv_code() { return tv_code;}

        public TextView getTv_detail() {return tv_detail;}

        public TextView getTv_id() {return tv_id;}

        public TextView getTv_photo() {
            return tv_photo;
        }

        public TextView getTv_price() {
            return tv_price;
        }

        public TextView getTv_status() {
            return tv_status;
        }

        public TextView getTv_type() {
            return tv_type;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_menu_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.getTv_name().setText("nama "+mlistMenu.get(position).getName());
        viewHolder.getTv_id().setText("id "+mlistMenu.get(position).getId());
        viewHolder.getTv_code().setText("code "+mlistMenu.get(position).getCode());
        viewHolder.getTv_price().setText("harga "+mlistMenu.get(position).getPrice());
        viewHolder.getTv_type().setText("tipe "+mlistMenu.get(position).getType());
        viewHolder.getTv_detail().setText("detail "+mlistMenu.get(position).getDetail());
        viewHolder.getTv_status().setText("status "+mlistMenu.get(position).getStatus());
        viewHolder.getTv_photo().setText("photo "+mlistMenu.get(position).getPhoto());
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent mIntent = new Intent(v.getContext(), DetailMenuActivity.class);
//                mIntent.putExtra("id",mlistMenu.get(position).getId());
//                mIntent.putExtra("nama",mlistMenu.get(position).getName());
//                mIntent.putExtra("code",mlistMenu.get(position).getCode());
//                mIntent.putExtra("harga",mlistMenu.get(position).getPrice());
//                mIntent.putExtra("tipe",mlistMenu.get(position).getType());
//                mIntent.putExtra("detail",mlistMenu.get(position).getDetail());
//                mIntent.putExtra("status",mlistMenu.get(position).getStatus());
//                mIntent.putExtra("photo",mlistMenu.get(position).getPhoto());
//                v.getContext().startActivity(mIntent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mlistMenu.size();
    }
}
