package com.mobcom.gakedaiorderapp.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;

import com.mobcom.gakedaiorderapp.model.menu.MenuModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CartItem {


    private MenuModel menu;
    private int quantity;

    public CartItem(MenuModel menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return getQuantity() == cartItem.getQuantity() &&
                getMenu().equals(cartItem.getMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenu(), getQuantity());
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "menu=" + menu +
                ", quantity=" + quantity +
                '}';
    }

    public static DiffUtil.ItemCallback<CartItem> itemCallback = new DiffUtil.ItemCallback<CartItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull CartItem oldItem, @NonNull @NotNull CartItem newItem) {
//            return oldItem.getMenu().equals(newItem.getMenu());
            return oldItem.getQuantity() == newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull CartItem oldItem, @NonNull @NotNull CartItem newItem) {
            return oldItem.equals(newItem);
        }
    };




}
