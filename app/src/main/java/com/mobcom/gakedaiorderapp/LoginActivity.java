package com.mobcom.gakedaiorderapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mobcom.gakedaiorderapp.api.ApiClient;
import com.mobcom.gakedaiorderapp.model.GetAuthModel;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    String AA, BB, CC, DD, EE, FF, GG, HH, II, JJ, KK, LL, MM, NN, OO, PP;
    TextView A = null, B = null, C = null, D = null, E = null, F = null, G = null, H = null, I = null, J = null, K = null, L = null, M = null, N = null, O = null, P = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.google_sign_in_btn);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        findViewById(R.id.google_sign_in_btn).setOnClickListener(this::onClick);


    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    public void updateUI(GoogleSignInAccount account) {

        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));

        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show();
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.google_sign_in_btn:
                signIn();
                break;
        }
    }

    private void signIn() {
        Log.d(TAG, "signIn: Initiated");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: Initiated");

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            String idToken = account.getIdToken();
            //getProfile(idToken);
            // Signed in successfully, show authenticated UI.
            Log.d(TAG, "handleSignInResult: Token " + idToken);
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            Log.w(TAG, "handleSignInResult:failed code =  " + e.getStatusCode());
            //updateUI(null);
        }
    }

    private void getProfileInfo() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
        }
    }

    private void getProfile(String idToken) {

        ApiClient.BASE_URL = "https://oauth2.googleapis.com/";
        ApiClient.endpoint().getAuth("tokeninfo?id_token=" + idToken).enqueue(new Callback<GetAuthModel>() {
            @Override
            public void onResponse(Call<GetAuthModel> call, Response<GetAuthModel> response) {

                Log.d(TAG, "onResponse: " + response.code());
                Log.d(TAG, "onResponse: " + idToken);
                Log.d(TAG, "onResponse: " + response.message());

                A.setText(response.body().getAuthModel().getIss());
                B.setText(response.body().getAuthModel().getAzp());
                C.setText(response.body().getAuthModel().getAud());
                D.setText(response.body().getAuthModel().getSub());
                E.setText(response.body().getAuthModel().getEmail());
                F.setText(response.body().getAuthModel().getEmailVerified());
                G.setText(response.body().getAuthModel().getName());
                H.setText(response.body().getAuthModel().getPicture());
                I.setText(response.body().getAuthModel().getGivenName());
                J.setText(response.body().getAuthModel().getFamilyName());
                K.setText(response.body().getAuthModel().getLocale());
                L.setText(response.body().getAuthModel().getIat());
                M.setText(response.body().getAuthModel().getExp());
                N.setText(response.body().getAuthModel().getAlg());
                O.setText(response.body().getAuthModel().getKid());
                P.setText(response.body().getAuthModel().getTyp());

                AA = A.toString();
                BB = B.toString();
                CC = C.toString();
                DD = D.toString();
                EE = E.toString();
                FF = F.toString();
                GG = G.toString();
                HH = H.toString();
                II = I.toString();
                JJ = J.toString();
                KK = K.toString();
                LL = L.toString();
                MM = M.toString();
                NN = N.toString();
                OO = O.toString();
                PP = P.toString();
                sentProfile(AA, BB, CC, DD, EE, FF, GG, HH, II, JJ, KK, LL, MM, NN, OO, PP);
            }

            @Override
            public void onFailure(Call<GetAuthModel> call, Throwable t) {

            }
        });
    }

    private void sentProfile(String aa, String bb, String cc, String dd, String ee, String ff, String gg, String hh, String ii, String jj, String kk, String ll, String mm, String nn, String oo, String pp) {
        ApiClient.endpoint().sendAuth(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp).enqueue(new Callback<GetAuthModel>() {
            @Override
            public void onResponse(Call<GetAuthModel> call, Response<GetAuthModel> response) {
                Log.d(TAG, "onResponse: Success sent to auth");
            }

            @Override
            public void onFailure(Call<GetAuthModel> call, Throwable t) {

            }
        });
    }


}