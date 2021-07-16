package com.mobcom.gakedaiorderapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.databinding.RvMenuItemBinding;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;

import org.jetbrains.annotations.NotNull;

public class MenuListAdapter extends ListAdapter<MenuModel, MenuListAdapter.MenuViewHolder> {

    MenuInterface menuInterface;
    public MenuListAdapter(MenuInterface menuInterface) {
        super(MenuModel.itemCallback);
        this.menuInterface = menuInterface;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvMenuItemBinding rvMenuItemBinding = RvMenuItemBinding.inflate(layoutInflater, parent, false);
        rvMenuItemBinding.setMenuInterface(menuInterface);
        return new MenuViewHolder(rvMenuItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MenuViewHolder holder, int position) {
        MenuModel menuModel = getItem(position);
        holder.rvMenuItemBinding.setMenu(menuModel);
        holder.rvMenuItemBinding.executePendingBindings();

    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        RvMenuItemBinding rvMenuItemBinding;

        public MenuViewHolder(RvMenuItemBinding binding) {
            super(binding.getRoot());
            this.rvMenuItemBinding = binding;

        }
    }

    public interface MenuInterface {
        void addItem(MenuModel menuModel);
        void onItemClick(MenuModel menuModel);
    }
}
