package com.example.android.kstories.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kstories.MainActivity;
import com.example.android.kstories.R;

import com.example.android.kstories.loggingin.LoggedInViewModel;
import com.example.android.kstories.loggingin.PreMainActivity;
import com.example.android.kstories.user.ui.gallery.GalleryFragment;
import com.example.android.kstories.user.ui.home.HomeFragment;
import com.example.android.kstories.user.ui.slideshow.SlideshowFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UserWelcomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private  DrawerLayout drawer;
    private TextView loggedInUserTextView;
    //private Button logOutButton;

    private LoggedInViewModel loggedInViewModel;
    private TextView messageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_welcome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_about, R.id.nav_browse,  R.id.nav_profile, R.id.nav_recordings)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        loggedInViewModel = ViewModelProviders.of(this).get(LoggedInViewModel.class);
        loggedInViewModel.getUserLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    loggedInUserTextView.setText("Logged In User: " + firebaseUser.getEmail());

                } else {

                }
            }
        });


        loggedInViewModel.getLoggedOutLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loggedOut) {
                if (loggedOut) {
                    Toast.makeText(UserWelcomeActivity.this, "User Logged Out", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserWelcomeActivity.this, PreMainActivity.class);
                    startActivity(intent);

                }
            }
        });

        loggedInUserTextView = findViewById(R.id.fragment_loggedin_loggedInUser);
//        logOutButton = findViewById(R.id.fragment_loggedin_logOut);
//
//        logOutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loggedInViewModel.logOut();
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_items, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorites) {
            Intent startFavoritesActivity = new Intent(this, FavoritesActivity.class);
            startActivity(startFavoritesActivity);
            return true;
        }

        if (id == R.id.action_logout) {
            loggedInViewModel.logOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}