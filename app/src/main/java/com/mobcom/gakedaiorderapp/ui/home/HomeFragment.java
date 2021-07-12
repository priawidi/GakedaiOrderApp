package com.mobcom.gakedaiorderapp.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.MenuAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentHomeBinding;
import com.mobcom.gakedaiorderapp.model.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.MenuModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    public static String Itembc = null;
    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView recyclerView, recyclerView2;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    HomeFragment homeFragment;
    MenuAdapter menuAdapter;
    View view;
    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        ApiClient.BASE_URL = "https://admin.gakedai.com/api/";
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
    }

    private void initialize(View view) {
        Log.d(TAG, "initialize: Initiated");
        recyclerView = view.findViewById(R.id.rv_menu);
        recyclerView2 = view.findViewById(R.id.rv_minum);
        //recyclerView = findViewById(R.id.rv_menu);
        //Recyclerview1
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        //Recyclerview2
        linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        recyclerView2.setHasFixedSize(true);
        homeFragment = this;
        getMakanFromApi();
        getMinumFromApi();


    }


    private void getMakanFromApi() {
        Log.d(TAG, "getMenuFromApi: Initiated");

        ApiClient.endpoint().getMakan().enqueue(new Callback<GetMenuModel>() {
            @Override
            public void onResponse(Call<GetMenuModel> call, Response<GetMenuModel> response) {

                Log.d(TAG, "onResponse: Get Makan Success");
                List<MenuModel> MenuList = response.body().getListDataMenu();
                mAdapter = new MenuAdapter(MenuList);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetMenuModel> call, Throwable t) {

            }
        });
    }

    private void getMinumFromApi(){
        Log.d(TAG, "getMinumFromApi: Initiated");
        ApiClient.endpoint().getMinum().enqueue(new Callback<GetMenuModel>() {
            @Override
            public void onResponse(Call<GetMenuModel> call, Response<GetMenuModel> response) {

                Log.d(TAG, "onResponse: Get MINUMMMMMM"+response.code());
                List<MenuModel> MinumList = response.body().getListDataMenu();
                mAdapter = new MenuAdapter(MinumList);
                mAdapter.notifyDataSetChanged();
                recyclerView2.setAdapter(mAdapter);
                Log.d(TAG, "onResponse: Get MINUMMMMMM"+MinumList);

            }

            @Override
            public void onFailure(Call<GetMenuModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}