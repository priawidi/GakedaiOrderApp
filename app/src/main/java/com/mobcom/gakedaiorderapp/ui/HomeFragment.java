package com.mobcom.gakedaiorderapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.MenuListAdapter;
import com.mobcom.gakedaiorderapp.databinding.FragmentHomeBinding;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;
import com.mobcom.gakedaiorderapp.viewmodel.MenuViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements MenuListAdapter.MenuInterface {

    private FragmentHomeBinding binding;
    private MenuListAdapter menuListAdapter;
    private MenuViewModel menuViewModel;
    NavController navController;
    LinearLayoutManager linearLayoutManager;

    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "initialize: Initiated");

        menuListAdapter = new MenuListAdapter(this);


        menuViewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        menuViewModel.getModel().observe(getViewLifecycleOwner(), new Observer<List<MenuModel>>() {
            @Override
            public void onChanged(List<MenuModel> menuModels) {
                if(menuModels != null){
                    menuListAdapter.submitList(menuModels);
                }


                Log.d(TAG, "onChanged: " + menuModels);
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvMenu.setLayoutManager(linearLayoutManager);
        binding.rvMenu.setHasFixedSize(true);
        binding.rvMenu.setNestedScrollingEnabled(false);
        menuListAdapter.notifyDataSetChanged();
        binding.rvMenu.setAdapter(menuListAdapter);
        navController = Navigation.findNavController(view);
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