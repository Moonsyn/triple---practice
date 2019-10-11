package com.example.practice.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class SignInActivity extends AppCompatActivity {

    private SignInButton mGoogleSignInButton;
    private LoginButton mFacebookSignInButton;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private GoogleSignInClient mGoogleSignInClient;

    private CallbackManager fbCallbackManager;

    private int RC_SIGN_IN = 500;
    private String TAG = "SignInActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(SignInActivity.this, gso);

        mGoogleSignInButton = findViewById(R.id.btnGoogleSignIn);
        mGoogleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        /*
        mFacebookSignInButton = findViewById(R.id.btnFacebookSignIn);
        mFacebookSignInButton.setPermissions("email", "public_profile");
        mFacebookSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fbCallbackManager = CallbackManager.Factory.create();
        mFacebookSignInButton.registerCallback(fbCallbackManager, new FacebookCallback<LoginResult>() {
            // 로그인에 성공하면 loginResult에 AccessToken과 최근에 부여되거나 거부된 권한 포함.
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Facebook Login Success");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "Facebook Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w(TAG, "Facebook Login is Failed.", error);
            }
        });
        */

        mAuth = FirebaseAuth.getInstance();

    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi,getSignInIntent{...};
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }catch (Exception e){
                // if Google Sign In failed, notify
                Log.w(TAG, "Google Sign In Failed", e);
            }

        }/*else{
            // Facebook Login Callback Result
            fbCallbackManager.onActivityResult(requestCode, resultCode, data);
        }*/
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        Log.d(TAG, "firebaseAuthWithGoogle: " + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
       firebaseAuthWithCredential(credential);
    }

    private void handleFacebookAccessToken(AccessToken token){
        Log.d(TAG, "handleFacebookAccessToken: "+ token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuthWithCredential(credential);
    }

    private void firebaseAuthWithCredential(AuthCredential credential){
        // 각 인증 방식으로 얻은 credential을 이용해서 Firebase 인증.
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Sign In Success, start MainActivity
                            Log.d(TAG, "signInWithCredential is Success");
                            startActivity(new Intent(SignInActivity.this, MainActivity.class));
                        }else{
                            // If Sign In is failed, display a message
                            Log.w(TAG, "signInWithCredential is Failed", task.getException());
                            Toast.makeText(SignInActivity.this, "Facebook Login is Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();
        }
    }

}
