package com.chethana.luxevista;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;
import android.widget.LinearLayout;

import com.chethana.luxevista.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                    replaceFragment(new reservationFragment());
                    auth.signOut();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
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
}