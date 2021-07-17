package com.mobcom.gakedaiorderapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.HistoryUserAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.model.order_item.GetOrderItemModel;
import com.mobcom.gakedaiorderapp.model.order_item.OrderItemModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private static final String TAG = "HistoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.rv_history_user);
        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();

        getOrderItem();

    }

    private void getOrderItem() {
        Intent intent = getIntent();
        String UniqueCode = intent.getStringExtra("UniqueCode");
        Log.d(TAG, "getOrderItem: " + UniqueCode);
        ApiClient.endpoint().getOrderItem("order_item/"+UniqueCode).enqueue(new Callback<GetOrderItemModel>() {
            @Override
            public void onResponse(Call<GetOrderItemModel> call, Response<GetOrderItemModel> response) {

                List<OrderItemModel> orderItemModels = response.body().getListOrderItem() ;
                HistoryUserAdapter historyUserAdapter = new HistoryUserAdapter(orderItemModels);
                recyclerView.setAdapter(historyUserAdapter);
            }

            @Override
            public void onFailure(Call<GetOrderItemModel> call, Throwable t) {

            }
        });
    }
}