package com.mobcom.gakedaiorderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.ActivityMainBinding;
import com.mobcom.gakedaiorderapp.model.GetGoogleUserModel;
import com.mobcom.gakedaiorderapp.model.PostToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //checkSignIn();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

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
    public void checkSignIn(){
        //Task<GoogleSignInAccount> task = mGoogleSignInClient.silentSignIn();
        mGoogleSignInClient.silentSignIn()
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<GoogleSignInAccount>() {
                            @Override
                            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                Log.d(TAG, "onComplete: Silent Sign In Complete");
                                //handleSignInResult(task);
                            }
                        });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            Log.d(TAG, "handleSignInResult: " +idToken);
            //sendToken(idToken);

            // TODO(developer): send ID Token to server and validate

            //updateUI(account);
        } catch (ApiException e) {
            Log.w(TAG, "handleSignInResult:error", e);
            //updateUI(null);
        }
    }
    public void updateUI(GoogleSignInAccount account) {

        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));

        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show();
        }

    }

    public void sendToken(String token){
        ApiClient.endpoint().sendToken(token).enqueue(new Callback<PostToken>() {
            @Override
            public void onResponse(Call<PostToken> call, Response<PostToken> response) {
                Log.d(TAG, "onResponse: Is Success");

            }

            @Override
            public void onFailure(Call<PostToken> call, Throwable t) {
                Log.d(TAG, "onFailure: Failed");

            }
        });
    }



}