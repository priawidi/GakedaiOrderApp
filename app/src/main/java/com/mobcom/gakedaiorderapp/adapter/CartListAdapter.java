package com.mobcom.gakedaiorderapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.databinding.RvCartItemBinding;
import com.mobcom.gakedaiorderapp.model.CartItem;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;


import org.jetbrains.annotations.NotNull;

import java.util.zip.Inflater;

public class CartListAdapter extends ListAdapter<CartItem, CartListAdapter.CartViewHolder> {
    MenuListAdapter menuListAdapter;
    private static final String TAG = "CartListAdapter";
    private Cartinterface cartinterface;

    public CartListAdapter(Cartinterface cartinterface) {
        super(CartItem.itemCallback);
        this.cartinterface = cartinterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvCartItemBinding rvCartItemBinding = RvCartItemBinding.inflate(layoutInflater, parent, false);
        return new CartViewHolder(rvCartItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartViewHolder holder, int position) {

        holder.rvCartItemBinding.setCartItem(getItem(position));
        holder.rvCartItemBinding.executePendingBindings();

    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        RvCartItemBinding rvCartItemBinding;

        public CartViewHolder(@NonNull @NotNull RvCartItemBinding rvCartItemBinding) {
            super(rvCartItemBinding.getRoot());
            this.rvCartItemBinding = rvCartItemBinding;

            rvCartItemBinding.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int quantity = 1;
                    cartinterface.changeQuantity(getItem(getAdapterPosition()));
                    Log.d(TAG, "onClick: " + cartinterface);
                    Log.d(TAG, "onClick: " + quantity);

//                    menuListAdapter.menuInterface.addItem(getItem(v.posit));
                }
            });

            rvCartItemBinding.btnMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int quantity = 1;

                    cartinterface.minusQuantity(getItem(getAdapterPosition()));
                    Log.d(TAG, "onClick: minus" + cartinterface);

//                    menuListAdapter.menuInterface.addItem(getItem(v.posit));
                }
            });
        }
    }

    public interface Cartinterface {
        void deleteItem(CartItem cartItem);

        void changeQuantity(CartItem cartItem);

        void minusQuantity(CartItem cartItem);
    }


}
