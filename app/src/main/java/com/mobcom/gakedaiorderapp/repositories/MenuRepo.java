package com.mobcom.gakedaiorderapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.model.menu.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuRepo {

    private MutableLiveData<List<MenuModel>> mutableMenuList;

    public LiveData<List<MenuModel>> getMenu(){
        if (mutableMenuList == null) {
            mutableMenuList = new MutableLiveData<>();
            loadMenu();
        }
        return mutableMenuList;
    }

    private void loadMenu(){

        ApiClient.endpoint().getMenu().enqueue(new Callback<GetMenuModel>() {
            @Override
            public void onResponse(Call<GetMenuModel> call, Response<GetMenuModel> response) {
                List<MenuModel> menuList = response.body().getListDataMenu();
                mutableMenuList.setValue(response.body().getListDataMenu());

            }

            @Override
            public void onFailure(Call<GetMenuModel> call, Throwable t) {

            }
        });
    }
}
