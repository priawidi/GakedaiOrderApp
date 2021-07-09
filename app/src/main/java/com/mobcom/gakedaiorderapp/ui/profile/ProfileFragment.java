package com.mobcom.gakedaiorderapp.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentProfileBinding;
import com.mobcom.gakedaiorderapp.model.AuthModel;
import com.mobcom.gakedaiorderapp.model.GetGoogleUserModel;
import com.mobcom.gakedaiorderapp.model.GoogleUserModel;
import com.mobcom.gakedaiorderapp.model.PostGoogleUserModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    private static final int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    TextView tv_email, tv_name;
    ImageView iv_photo;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: Initiated");
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: Initiated");
        tv_email = view.findViewById(R.id.tv_profile_email);
        tv_name = view.findViewById(R.id.tv_profile_name);
        iv_photo = view.findViewById(R.id.iv_profile);


        SignInButton signInButton = view.findViewById(R.id.google_sign_in_btn);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        view.findViewById(R.id.google_sign_in_btn).setOnClickListener(this);
        view.findViewById(R.id.sign_in_btn).setOnClickListener(this);


    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: Initiated");
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        String idToken = account.getIdToken();
        getAuthInfo(idToken);

        Log.d(TAG, "onStart: " + account);
        //updateUI(account);

    }



    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: Destroyed");
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in_btn:
                signIn();
                break;

            case R.id.sign_in_btn:
                signOut();
                break;
        }
        
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: Initiated");

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if(requestCode == RC_SIGN_IN){
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try{
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String idToken = account.getIdToken();
            getAuthInfo(idToken);
            getProfileInfo();

            //updateUI(account);
            Log.d(TAG, "handleSignInResult: Token " + idToken);
            Log.d(TAG, "handleSignInResult: account " + account);
            // Signed in successfully, show authenticated UI.
        }catch (ApiException e){
            // The ApiException status code indicates the detailed failure reason.
            Log.w(TAG, "handleSignInResult:failed code =  "+e.getStatusCode());
            //updateUI(null);
        }
    }


    private void getProfileInfo() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (account != null) {
            String idToken = account.getIdToken();
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();

//            Log.d(TAG, "getProfileInfo: Token " + idToken);
//            Log.d(TAG, "getProfileInfo: " + personName);
//            Log.d(TAG, "getProfileInfo: " + personGivenName);
//            Log.d(TAG, "getProfileInfo: " + personEmail);
//            Log.d(TAG, "getProfileInfo: " + personId);
//            Log.d(TAG, "getProfileInfo: " + personPhoto);
            //getAuthInfo(idToken);
            //sendProfileInfoToServer(personEmail, personName, personPhoto, personGivenName, personFamilyName);
            //getUserProfileInfo();
        }
    }

    private void getAuthInfo(String idToken) {
        ApiClient.BASE_URL = "https://oauth2.googleapis.com/";
        ApiClient.endpoint(). getAuth("tokeninfo?id_token="+idToken).enqueue(new Callback<AuthModel>() {
            @Override
            public void onResponse(Call<AuthModel> call, Response<AuthModel> response) {
                tv_email.setText(response.body().getEmail());
                tv_name.setText(response.body().getName());
                Picasso.get().load(response.body().getPicture()).into(iv_photo);
            }

            @Override
            public void onFailure(Call<AuthModel> call, Throwable t) {

            }
        });

    }

    private void sendProfileInfoToServer(String email, String name, Uri photo, String givenName, String familyName) {
        ApiClient.endpoint().sendUser(email, name, photo,givenName, familyName).enqueue(new Callback<PostGoogleUserModel>() {
            @Override
            public void onResponse(Call<PostGoogleUserModel> call, Response<PostGoogleUserModel> response) {

                Log.d(TAG, "onResponse: Success " + response.code());

            }

            @Override
            public void onFailure(Call<PostGoogleUserModel> call, Throwable t) {
                Log.d(TAG, "onResponse: Failed" );

            }
        });
    }

    private void getUserProfileInfo() {
        ApiClient.endpoint().getUser().enqueue(new Callback<GetGoogleUserModel>() {
            @Override
            public void onResponse(Call<GetGoogleUserModel> call, Response<GetGoogleUserModel> response) {
                //List<GoogleUserModel> UserList = response.body().getListDataUser();
                Log.d(TAG, "onResponse: Success " +response.code());
                tv_email.setText(response.body().getGoogleUserModel().getEmail());
                tv_name.setText(response.body().getGoogleUserModel().getName());
                Picasso.get().load(response.body().getGoogleUserModel().getPicture()).into(iv_photo);
            }

            @Override
            public void onFailure(Call<GetGoogleUserModel> call, Throwable t) {

            }
        });
    }
    private void signIn() {
        Log.d(TAG, "signIn: Initiated");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Toast toast = Toast.makeText(getContext(),"Signed In",Toast.LENGTH_SHORT);
        toast.show();

    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "onComplete: User Logout Success");
                        Toast toast = Toast.makeText(getContext(),"Signed Out",Toast.LENGTH_SHORT);
                        toast.show();
                        revokeAccess();
                    }
                });
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "onComplete: User Credentials Removed From App");
                        // ...
                    }
                });
    }

}