package com.mobcom.gakedaiorderapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.MenuAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentHomeBinding;
import com.mobcom.gakedaiorderapp.model.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.MenuModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager linearLayoutManager;
    HomeFragment homeFragment;
    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ApiClient.BASE_URL = "https://admin.gakedai.com/api/";

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
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
        //recyclerView = findViewById(R.id.rv_menu);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        homeFragment = this;
        getMenuFromApi();

    }

    private void getMenuFromApi() {
        Log.d(TAG, "getMenuFromApi: Initiated");
        ApiClient.endpoint().getMenu().enqueue(new Callback<GetMenuModel>() {
            @Override
            public void onResponse(Call<GetMenuModel> call, Response<GetMenuModel> response) {
                List<MenuModel> MenuList = response.body().getListDataMenu();
//                Log.d(TAG, "onResponse: Jumlah Data Menu = " + String.valueOf(MenuList.size()));
                mAdapter = new MenuAdapter(MenuList);
                mAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(mAdapter);

                Log.d(TAG, "onResponse: Success ");
                Log.d(TAG, "onResponse: Success " + MenuList);
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