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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mobcom.gakedaiorderapp.LoginActivity;
import com.mobcom.gakedaiorderapp.MainActivity;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.databinding.FragmentProfileBinding;
import com.mobcom.gakedaiorderapp.model.AuthModel;
import com.mobcom.gakedaiorderapp.model.GetAuthModel;
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
        //profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: Initiated");
        tv_email = view.findViewById(R.id.tv_profile_email);
        tv_name = view.findViewById(R.id.tv_profile_name);
        iv_photo = view.findViewById(R.id.iv_profile);

        view.findViewById(R.id.sign_in_btn).setOnClickListener(this);


    }



    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {
            String IdToken = acct.getIdToken();
            //getProfile(IdToken);
            getProfileInfo();
        }
        super.onStart();
    }
    private void getProfile(String idToken) {
        ApiClient.BASE_URL="https://oauth2.googleapis.com/";
        ApiClient.endpoint().getAuth("tokeninfo?id_token="+idToken).enqueue(new Callback<GetAuthModel>() {
            @Override
            public void onResponse(Call<GetAuthModel> call, Response<GetAuthModel> response) {

                tv_name.setText(response.body().getAuthModel().getName());
                tv_email.setText(response.body().getAuthModel().getEmail());
                Picasso.get().load(response.body().getAuthModel().getPicture()).into(iv_photo);
            }

            @Override
            public void onFailure(Call<GetAuthModel> call, Throwable t) {

            }
        });
    }
    private void getProfileInfo() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            tv_name.setText(personName);
            tv_email.setText(personEmail);
            Picasso.get().load(personPhoto).into(iv_photo);


        }
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
            case R.id.sign_in_btn:
                signOut();
                break;
        }
        
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
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                });
    }

}