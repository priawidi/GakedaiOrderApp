package com.mobcom.gakedaiorderapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.MenuAdapter;
import com.mobcom.gakedaiorderapp.adapter.MenuListAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentHomeBinding;
import com.mobcom.gakedaiorderapp.model.menu.GetMenuModel;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;
import com.mobcom.gakedaiorderapp.viewmodel.MenuViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements MenuListAdapter.MenuInterface {

    private FragmentHomeBinding binding;
    private MenuListAdapter menuListAdapter;
    private MenuViewModel menuViewModel;
    NavController navController;
    RecyclerView recyclerView, recyclerView2;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    HomeFragment homeFragment;
    MenuAdapter menuAdapter;
    View view;
    ProgressBar progressBar;
    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        menuViewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        menuViewModel.getModel().observe(getViewLifecycleOwner(), new Observer<List<MenuModel>>() {
            @Override
            public void onChanged(List<MenuModel> menuModels) {
                menuListAdapter.submitList(menuModels);
                Log.d(TAG, "onChanged: " + menuModels);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
    }

    private void initialize(@NotNull View view) {
        Log.d(TAG, "initialize: Initiated");
        progressBar = view.findViewById(R.id.loading_bar);
        recyclerView = view.findViewById(R.id.rv_menu);
//        recyclerView2 = view.findViewById(R.id.rv_minum);
//        //recyclerView = findViewById(R.id.rv_menu);
//        //Recyclerview1
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
//        //Recyclerview2
//        linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView2.setLayoutManager(linearLayoutManager2);
//        recyclerView2.setHasFixedSize(true);
//        recyclerView2.setNestedScrollingEnabled(false);
//        homeFragment = this;

        menuListAdapter = new MenuListAdapter(this);
//        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        binding.rvMenu.setLayoutManager(linearLayoutManager);
//        binding.rvMenu.setHasFixedSize(true);
//        binding.rvMenu.setNestedScrollingEnabled(false);
        binding.rvMenu.setAdapter(menuListAdapter);

        menuViewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        menuViewModel.getModel().observe(getViewLifecycleOwner(), new Observer<List<MenuModel>>() {
            @Override
            public void onChanged(List<MenuModel> menuModels) {
                menuListAdapter.submitList(menuModels);
                Log.d(TAG, "onChanged: " + menuModels);
                //progressBar.setVisibility(View.GONE);
            }
        });

        navController = Navigation.findNavController(view);

        //getMakanFromApi();
        //getMinumFromApi();


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

                progressBar.setVisibility(View.GONE);
                //view.findViewById(R.id.loading_bar).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetMenuModel> call, Throwable t) {

            }
        });
    }

    private void getMinumFromApi() {
        Log.d(TAG, "getMinumFromApi: Initiated");
        ApiClient.endpoint().getMinum().enqueue(new Callback<GetMenuModel>() {
            @Override
            public void onResponse(Call<GetMenuModel> call, Response<GetMenuModel> response) {

                Log.d(TAG, "onResponse: Get MINUMMMMMM" + response.code());
                List<MenuModel> MinumList = response.body().getListDataMenu();
                mAdapter = new MenuAdapter(MinumList);
                mAdapter.notifyDataSetChanged();
                recyclerView2.setAdapter(mAdapter);
                Log.d(TAG, "onResponse: Get MINUMMMMMM" + MinumList);

                //view.findViewById(R.id.loading_bar).setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetMenuModel> call, Throwable t) {

            }
        });
    }



    @Override
    public void addItem(MenuModel menuModel) {

        Log.d(TAG, "addItem: " + menuModel.toString());
        boolean isAdded = menuViewModel.addItemToCart(menuModel);

//        Log.d(TAG, "addItem: " + menuModel.getName() + isAdded);
        if (isAdded) {
            Snackbar.make(requireView(), menuModel.getName() + " added to cart.", Snackbar.LENGTH_LONG)
                    .setAction("Checkout", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            navController.navigate(R.id.action_navigation_home_to_navigation_cart);
                        }
                    }).show();
        }
    }

    @Override
    public void onItemClick(MenuModel menuModel) {
        Log.d(TAG, "onItemClick: " + menuModel.toString());

        menuViewModel.setMenu(menuModel);
        Log.d(TAG, "onItemClick: " + menuViewModel.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}