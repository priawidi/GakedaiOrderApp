package com.mobcom.gakedaiorderapp.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.ActivityMainBinding;
import com.mobcom.gakedaiorderapp.model.CartItem;
import com.mobcom.gakedaiorderapp.model.google.GetGoogleUserModel;
import com.mobcom.gakedaiorderapp.viewmodel.MenuViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    MenuViewModel menuViewModel;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        menuViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                Log.d(TAG, "onChanged: " + cartItems.toString());
                Log.d(TAG, "onChanged: " + cartItems.size());
            }
        });
        sendUserInfo();
    }

    private void sendUserInfo() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct != null ){
            String pName = acct.getDisplayName();
            String pGivenName = acct.getGivenName();
            String pFamilyName = acct.getFamilyName();
            String pEmail = acct.getEmail();
            String pId = acct.getId();
            Log.d(TAG, "sendUserInfo: " +pId);
            Uri pPhoto = acct.getPhotoUrl();
            ApiClient.endpoint().sendUser(pId, pEmail,pName,pPhoto,pGivenName,pFamilyName).enqueue(new Callback<GetGoogleUserModel>() {
                @Override
                public void onResponse(Call<GetGoogleUserModel> call, Response<GetGoogleUserModel> response) {

                    Log.d(TAG, "onResponse: Success Sent User");
                }

                @Override
                public void onFailure(Call<GetGoogleUserModel> call, Throwable t) {

                    Log.d(TAG, "onFailure: Failde");
                }
            });
        }

    }
    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }

    }
}