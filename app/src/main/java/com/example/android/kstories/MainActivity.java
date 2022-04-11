package com.example.android.kstories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.android.kstories.user.UserWelcomeActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

//    private FirebaseAuth mFirebaseAuth;
//    private FirebaseAuth.AuthStateListener mAuthStateListener;
//
//    private static final int RC_SIGN_IN = 1;
//    public static final String ANONYMOUS = "anonymous";
    private String mUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mUsername = ANONYMOUS;
//
//        //Initialize Firebase Autehntication
//        mFirebaseAuth = FirebaseAuth.getInstance();

        // Find the View that shows the Lauryn Hill songs category
        Button lauryn = (Button) findViewById(R.id.user_account_button);

        // Set a click listener on that View
        lauryn.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
            @Override
            public void onClick(View view) {
                Intent laurynIntent = new Intent(MainActivity.this, UserWelcomeActivity.class);
                startActivity(laurynIntent);


            }
        });

//        // Find the View that shows the Lauryn Hill songs category
//        Button logout = (Button) findViewById(R.id.logout);
//
//        // Set a click listener on that View
//        logout.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the Lauryn Hill songs View is clicked on.
//            @Override
//            public void onClick(View view) {
//                AuthUI.getInstance().signOut(MainActivity.this);
//
//            }
//        });


//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // user is signed in
//                    Toast.makeText(MainActivity.this, "You are signed in." + user.getDisplayName(), Toast.LENGTH_SHORT).show();
//                    onSignedInInitialize(user.getDisplayName());
//                } else {
//                    // user is signed out
//                    onSignedOutCleanup();
//                    // Choose authentication providers
//                    List<AuthUI.IdpConfig> providers = Arrays.asList(
//                            new AuthUI.IdpConfig.EmailBuilder().build(),
//                            new AuthUI.IdpConfig.GoogleBuilder().build());
//
//                    // Create and launch sign-in intent
//                    startActivityForResult(
//                            AuthUI.getInstance()
//                                    .createSignInIntentBuilder()
//                                    .setIsSmartLockEnabled(false)
//                                    .setAvailableProviders(providers)
//                                    .build(),
//                            RC_SIGN_IN);
//                }
//            }
//        };

    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
//    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        if (mAuthStateListener != null) {
//            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
//        }
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            if (resultCode == RESULT_OK) {
//                // Sign-in succeeded, set up the UI
//                Toast.makeText(this, "Signed in!", Toast.LENGTH_SHORT).show();
//            } else if (resultCode == RESULT_CANCELED) {
//                // Sign in was canceled by the user, finish the activity
//                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.sign_out_menu:
//                //sign out
//                AuthUI.getInstance().signOut(this);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    private void onSignedInInitialize(String username) {
//        mUsername = username;
//
//    }
//
//    private void onSignedOutCleanup() {
//        mUsername = ANONYMOUS;
//
//    }

}



