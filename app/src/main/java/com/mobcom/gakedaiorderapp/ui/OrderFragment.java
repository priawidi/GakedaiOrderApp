package com.mobcom.gakedaiorderapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.CartListAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentCartBinding;
import com.mobcom.gakedaiorderapp.databinding.FragmentOrderBinding;
import com.mobcom.gakedaiorderapp.viewmodel.MenuViewModel;

import org.jetbrains.annotations.NotNull;

public class OrderFragment extends Fragment {

    private static final String TAG = "OrderFragment";
    NavController navController;
    FragmentOrderBinding binding;
    MenuViewModel menuViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Bundle bundle = this.getArguments();
        Log.d(TAG, "onCreateView: " + bundle);
        if (bundle != null) {
            String value1 = bundle.getString("Code");
            binding.tvKodeCheckout.setText(value1);
        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull  View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        Log.d(TAG, "ViewCreated : " + bundle);
        navController = Navigation.findNavController(view);
        menuViewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        binding.btnContinueMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                menuViewModel.resetCart();
                navController.navigate(R.id.action_navigation_order_to_navigation_home);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}