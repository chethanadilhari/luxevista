package com.chethana.luxevista;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button exploreButton = findViewById(R.id.exploreButton);
        Button servicesButton = findViewById(R.id.servicesButton);
        Button reservationsButton = findViewById(R.id.infoReservationsButton);

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