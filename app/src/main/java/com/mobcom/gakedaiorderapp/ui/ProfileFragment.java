package com.mobcom.gakedaiorderapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mobcom.gakedaiorderapp.R;
import com.mobcom.gakedaiorderapp.databinding.FragmentProfileBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ProfileFragment";
    private static final int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
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