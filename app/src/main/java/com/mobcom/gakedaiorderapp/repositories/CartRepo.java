package com.mobcom.gakedaiorderapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobcom.gakedaiorderapp.model.CartItem;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {
    private MutableLiveData<List<CartItem>> mutableCart = new MutableLiveData<>();
    private MutableLiveData<Integer> mutableTotalPrice = new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart(){
        if (mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    public boolean addItemtoCart(MenuModel menuModel){
        if (mutableCart.getValue() == null){
            initCart();
        }

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (CartItem cartItem: cartItemList){
            if(cartItem.getMenu().getId().equals(menuModel.getId())){
                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemList.set(index, cartItem);

                mutableCart.setValue(cartItemList);
                calculateCartTotal();

                return true;
            }
        }

        CartItem cartItem = new CartItem(menuModel, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem){
        if (mutableCart.getValue() == null){
            return;
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem){
        if (mutableCart.getValue() == null) return;

        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        int quantity = cartItem.getQuantity();
        CartItem updatedItem = new CartItem(cartItem.getMenu(), quantity + 1);
        cartItemList.set(cartItemList.indexOf(cartItem), updatedItem);

        mutableCart.setValue(cartItemList);
        calculateCartTotal();

    }
    public void minusQuantity(CartItem cartItem){
        if (mutableCart.getValue() == null) return;


        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());

        int quantity = cartItem.getQuantity();
        if(quantity >0){
            CartItem updatedItem = new CartItem(cartItem.getMenu(), quantity - 1);
            cartItemList.set(cartItemList.indexOf(cartItem), updatedItem);

            mutableCart.setValue(cartItemList);
            calculateCartTotal();
        }else if (quantity == 0){
            removeItemFromCart(cartItem);
        }



    }

    private void calculateCartTotal(){
        if(mutableCart.getValue()==null) return;
        int total = 0;
        List<CartItem> cartItemList = mutableCart.getValue();
        for(CartItem cartItem : cartItemList ){
            total += Integer.parseInt(cartItem.getMenu().getPrice()) * cartItem.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }

    public LiveData<Integer> getTotalPrice(){
        if (mutableTotalPrice.getValue() ==null ){
            mutableTotalPrice.setValue(0);
        }
        return mutableTotalPrice;

    }
}
