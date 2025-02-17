package com.chethana.luxevista;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class LocationDetailsActivity extends AppCompatActivity {

    private TextView locationNameTXT;
    private TextView locationDescriptionTXT;
    private ImageView locationImageImg,backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location_details);


        // Initialize views
        locationNameTXT = findViewById(R.id.locationName);
        locationDescriptionTXT = findViewById(R.id.locationDescription);
        locationImageImg = findViewById(R.id.locationImage);

        // Retrieve data passed from the adapter
        String locationName = getIntent().getStringExtra("locationName");
        String locationDescription = getIntent().getStringExtra("locationDescription");
        String locationImage = getIntent().getStringExtra("locationImage");

        // Set the data into views
        locationNameTXT.setText(locationName);
        locationDescriptionTXT.setText(locationDescription);

        // Load the image using Glide
        Glide.with(this)
                .load(locationImage)
                .apply(new RequestOptions().placeholder(R.drawable.home_bg))
                .into(locationImageImg);


        ImageView backButton = findViewById(R.id.locationbackIcon);
        backButton.setOnClickListener(v -> finish());
    }



}