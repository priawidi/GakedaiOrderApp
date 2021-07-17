package com.mobcom.gakedaiorderapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridLayout;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.FavoriteAddAdapter;
import com.mobcom.gakedaiorderapp.adapter.HistoryUserAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.model.menu.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;
import com.mobcom.gakedaiorderapp.model.order_item.OrderItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private static final String TAG = "FavoriteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        recyclerView = findViewById(R.id.rv_favorite_add);
        gridLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.hasFixedSize();

        getMenuItem();
    }

    private void getMenuItem() {

        ApiClient.endpoint().getMenu().enqueue(new Callback<GetMenuModel>() {
            @Override
            public void onResponse(Call<GetMenuModel> call, Response<GetMenuModel> response) {
                Log.d(TAG, "onResponse: " + response.code());

                List<MenuModel> favlist = response.body().getListDataMenu() ;
                FavoriteAddAdapter favoriteAddAdapter = new FavoriteAddAdapter(favlist);
                recyclerView.setAdapter(favoriteAddAdapter);
            }

            @Override
            public void onFailure(Call<GetMenuModel> call, Throwable t) {

            }
        });
    }
}