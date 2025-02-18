package com.chethana.luxevista;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.chethana.luxevista.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding binding;
    private FirebaseAuth auth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Hooks*/
        drawerLayout = findViewById(R.id.homeScreen);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

navigationView.setCheckedItem(R.id.home);

        ImageView menuIcon = findViewById(R.id.menuIcon);
        menuIcon.setOnClickListener(v -> drawerLayout.open());

        auth = FirebaseAuth.getInstance();

        LinearLayout homeNavButtons = findViewById(R.id.homeNavButtons);

        Button exploreButton = findViewById(R.id.exploreButton);
        Button servicesButton = findViewById(R.id.servicesButton);
        Button reservationsButton = findViewById(R.id.infoReservationsButton);

        binding.bottomNavigationMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    selectedButton(exploreButton);
                    homeNavButtons.setVisibility(LinearLayout.VISIBLE);
                    replaceFragment(new ExploreFragment());
                    break;
                case R.id.rooms:
                    homeNavButtons.setVisibility(LinearLayout.GONE);
                    replaceFragment(new RoomsMenuFragment());
                    break;
                case R.id.services:
                    homeNavButtons.setVisibility(LinearLayout.GONE);
                    replaceFragment(new ServiceMenuFragment());
                    break;
                case R.id.bookings:
                    homeNavButtons.setVisibility(LinearLayout.GONE);
                    replaceFragment(new BookingFragment());
                    break;
            }
            return true;

        });



        // initial stage loading
        replaceFragment(new ExploreFragment());
        exploreButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.jungleGreen));
        exploreButton.setTextColor(getResources().getColor(R.color.white));


        exploreButton.setOnClickListener(v -> {
            replaceFragment(new ExploreFragment());
            selectedButton(exploreButton);
        });

        servicesButton.setOnClickListener(v -> {
            replaceFragment(new ServicesFragment());
            selectedButton(servicesButton);
        });

        reservationsButton.setOnClickListener(v -> {
            replaceFragment(new reservationFragment());
            selectedButton(reservationsButton);
        });

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }

    private void selectedButton(Button button) {
        Button exploreButton = findViewById(R.id.exploreButton);
        Button servicesButton = findViewById(R.id.servicesButton);
        Button reservationsButton = findViewById(R.id.infoReservationsButton);

        exploreButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.darkGrey));
        exploreButton.setTextColor(getResources().getColor(R.color.jungleGreen));
        servicesButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.darkGrey));
        servicesButton.setTextColor(getResources().getColor(R.color.jungleGreen));
        reservationsButton.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.darkGrey));
        reservationsButton.setTextColor(getResources().getColor(R.color.jungleGreen));

        button.setTextColor(getResources().getColor(R.color.white));
        button.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.jungleGreen));
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.home:
                selectedFragment = new ExploreFragment();
                break;

            case R.id.rooms:
                selectedFragment = new RoomsMenuFragment();
                break;

            case R.id.services:
                selectedFragment = new ServiceMenuFragment();
                break;

            case R.id.bookings:
                selectedFragment = new BookingFragment();
                break;

            case R.id.info:
                selectedFragment = new reservationFragment();
                break;

            case R.id.profile:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class)); // For Profile Activity
                return true;

            case R.id.notifications:
                startActivity(new Intent(HomeActivity.this, NotificationsActivity.class)); // For Notifications Activity
                return true;

            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.settings:
                startActivity(new Intent(HomeActivity.this,SettingsActivity.class)); // For Notifications Activity
                return true;

            case R.id.logout:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
                return true;
        }

        // If a fragment was selected, replace the fragment container
        if (selectedFragment != null) {
            replaceFragment(selectedFragment);
        }

        drawerLayout.closeDrawer(GravityCompat.START); // Close the drawer after selection
        return true;
    }

}