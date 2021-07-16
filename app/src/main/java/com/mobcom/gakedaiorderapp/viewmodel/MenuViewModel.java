package com.mobcom.gakedaiorderapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mobcom.gakedaiorderapp.model.CartItem;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;
import com.mobcom.gakedaiorderapp.repositories.CartRepo;
import com.mobcom.gakedaiorderapp.repositories.MenuRepo;

import java.util.List;

public class MenuViewModel extends ViewModel {

    MenuRepo menuRepo = new MenuRepo();
    CartRepo cartRepo = new CartRepo();


    MutableLiveData<MenuModel> mutableMenuModel = new MutableLiveData<>();

    public LiveData<List<MenuModel>> getModel(){
        return menuRepo.getMenu();
    }

    public void setMenu(MenuModel menuModel){
        mutableMenuModel.setValue(menuModel);
    }

    public LiveData<MenuModel> getMenu() { return mutableMenuModel;}

    public LiveData<List<CartItem>> getCart() {
        return cartRepo.getCart();
    }

    public boolean addItemToCart(MenuModel menuModel){
        return cartRepo.addItemtoCart(menuModel);
    }

    public void removeItemFromCart(CartItem cartItem) {
        cartRepo.removeItemFromCart(cartItem);
    }

    public void changeQuantity(CartItem cartItem){
        cartRepo.changeQuantity(cartItem);
    }
    public void minusQuantity(CartItem cartItem){
        cartRepo.minusQuantity(cartItem);
    }
    public LiveData<Integer> getTotalPrice(){
        return  cartRepo.getTotalPrice();
    }

    public void resetCart(){
        cartRepo.initCart();
    }
 }
