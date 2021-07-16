package com.mobcom.gakedaiorderapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.adapter.CartAdapter;
import com.mobcom.gakedaiorderapp.adapter.CartListAdapter;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentCartBinding;
import com.mobcom.gakedaiorderapp.model.CartItem;
import com.mobcom.gakedaiorderapp.model.cart.CartModel;
import com.mobcom.gakedaiorderapp.model.cart.GetCartModel;
import com.mobcom.gakedaiorderapp.model.cart.PostCartModel;
import com.mobcom.gakedaiorderapp.model.menu.MenuModel;
import com.mobcom.gakedaiorderapp.model.order_history.PostOrderHistoryModel;
import com.mobcom.gakedaiorderapp.model.order_item.PostOrderItemModel;
import com.mobcom.gakedaiorderapp.viewmodel.MenuViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment implements CartListAdapter.Cartinterface {
    private Context context;
    private static final String TAG = "CartFragment";
    MenuViewModel menuViewModel;
    CartListAdapter cartListAdapter;
    private FragmentCartBinding binding;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    LinearLayoutManager linearLayoutManager;
    CartFragment cartFragment;
    NavController navController;
    Spinner spinner;
    String[] meja = {"1","2","3","4","5","6","7","8","9","10"};
    String MejaId;

    int index = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        recyclerView = view.findViewById(R.id.rv_cart);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        cartFragment = this;
        context = view.getContext();

        //Spinner
        //spinner = view.findViewById(R.id.spinner_meja);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, meja);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerMeja.setAdapter(arrayAdapter);
        binding.spinnerMeja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MejaId = binding.spinnerMeja.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CartListAdapter cartListAdapter = new CartListAdapter(this);
        binding.rvCart.setAdapter(cartListAdapter);
        binding.rvCart.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL));
        menuViewModel = new ViewModelProvider(requireActivity()).get(MenuViewModel.class);
        menuViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                Log.d(TAG, "onChanged: " + cartItems);
                binding.btnCheckout.setEnabled(cartItems.size() > 0);
                index = cartItems.size();

            }
        });
        menuViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.tvTotalOrderCart.setText("Total : Rp " + integer.toString());
                //Log.d(TAG, "onChanged Cart: " + menuViewModel.getCart());
            }
        });

        binding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + menuViewModel.getCart().getValue().toString());
                Date date = new Date();
                long strdate = date.getTime();
                String UniqueCode = strdate + "-" + index + "-" + MejaId;
                String totalHarga = menuViewModel.getTotalPrice().getValue().toString();
                sendOrderToKitchen(totalHarga, UniqueCode);
//                getOrderFromKitchen();
                for (int i = 0; i < index; i++) {
                    String Name = menuViewModel.getCart().getValue().get(i).getMenu().getName();
                    String Price = menuViewModel.getCart().getValue().get(i).getMenu().getPrice();
                    String Photo = menuViewModel.getCart().getValue().get(i).getMenu().getPhoto();
                    String Qty = Integer.toString(menuViewModel.getCart().getValue().get(i).getQuantity());
                    String totalPrice = String.valueOf(Integer.valueOf(Price) * Integer.valueOf(Qty));
                    Log.d(TAG, "onClick: " + Name);


                    sendOrderItem(Qty, Name, totalPrice, Photo, UniqueCode, MejaId);
                }
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                OrderFragment fragment = new OrderFragment();
//                Bundle bundle = new Bundle();
//                bundle.putString("Code", UniqueCode);
//                fragment.setArguments(bundle);
//                fragmentTransaction.add(R.id.fragment_container, fragment).commit();
//                Log.d(TAG, "onClick: NBUNDLE" + bundle);

                Intent intent = new Intent(getActivity().getBaseContext(), OrderActivity.class);
                intent.putExtra("Code", UniqueCode);
                getActivity().startActivity(intent);
                Log.d(TAG, "onClick: " + intent);
            }
        });
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        menuViewModel.removeItemFromCart(cartItem);
        Log.d(TAG, "deleteItem: " + cartItem.getMenu().getName());
    }


    @Override
    public void changeQuantity(CartItem cartItem) {
        menuViewModel.changeQuantity(cartItem);
    }

    @Override
    public void minusQuantity(CartItem cartItem) {
        menuViewModel.minusQuantity(cartItem);
    }


    public void sendOrderItem(String itemQty, String itemName, String itemPrice, String itemPhoto, String uniqueCode, String mejaId) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        String userId = acct.getId();
        String userName = acct.getDisplayName();
        ApiClient.endpoint().sendOrder(userId, userName, itemQty, itemName, itemPrice, itemPhoto, uniqueCode, mejaId).enqueue(new Callback<PostOrderItemModel>() {
            @Override
            public void onResponse(Call<PostOrderItemModel> call, Response<PostOrderItemModel> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                }
                Log.d(TAG, "onResponse: Success");
            }

            @Override
            public void onFailure(Call<PostOrderItemModel> call, Throwable t) {

            }
        });


    }

    public void sendOrderToKitchen(String total_price, String unique_code){

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(context);
        String userId = acct.getId();
        String userName = acct.getDisplayName();
        ApiClient.endpoint().sendHistory(userId, userName, total_price, unique_code, MejaId ).enqueue(new Callback<PostOrderHistoryModel>() {
            @Override
            public void onResponse(Call<PostOrderHistoryModel> call, Response<PostOrderHistoryModel> response) {
                Log.d(TAG, "onResponse: Success");

            }

            @Override
            public void onFailure(Call<PostOrderHistoryModel> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed");

            }
        });

    }

    private void getOrderFromKitchen() {
//        ApiClient.endpoint().
    }



}