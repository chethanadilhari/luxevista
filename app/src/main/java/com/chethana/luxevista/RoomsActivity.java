package com.chethana.luxevista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RoomsActivity extends AppCompatActivity {

    com.google.android.material.imageview.ShapeableImageView roomImage;
    TextView roomName, roomDescription, roomPriceText, roomMaxPax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        roomImage = findViewById(R.id.roomImage);
        roomName = findViewById(R.id.roomName);
        roomDescription = findViewById(R.id.roomDescription);
        roomPriceText = findViewById(R.id.roomPrice);
        roomMaxPax =findViewById(R.id.roomMaxPax);

        // Get room details from intent
        String roomId = getIntent().getStringExtra("roomId");
        String roomNameText = getIntent().getStringExtra("roomName");
        String roomImageUrl = getIntent().getStringExtra("roomImageUrl");
        String roomDescriptionText = getIntent().getStringExtra("roomDescription");
        Long roomPrice = getIntent().getLongExtra("roomPrice", 0);
        int maxPaxValue = getIntent().getIntExtra("roomMaxPax", 0);

        String price = "Price: $" + roomPrice.toString();
        String maxpax = "Max Pax: " + maxPaxValue;

        // Set Values
        roomName.setText(roomNameText);
        roomDescription.setText(roomDescriptionText);
        roomPriceText.setText(price);
        roomMaxPax.setText(maxpax);

        Glide.with(this)
            .load(roomImageUrl)
            .into(roomImage);

        ImageView backButton = findViewById(R.id.backIcon);
        backButton.setOnClickListener(v -> finish()); // Closes the current activity

        Button bookButton = findViewById(R.id.btnBookNow);
        bookButton.setOnClickListener(v -> {
            // Open Booking Activity
            Intent intent = new Intent(RoomsActivity.this, BookingDetailsActivity.class);
            intent.putExtra("roomId", roomId);
            intent.putExtra("roomName", roomNameText);
            intent.putExtra("roomImageUrl", roomImageUrl);
            intent.putExtra("roomPrice", roomPrice);
            intent.putExtra("roomMaxPax",maxPaxValue);
            startActivity(intent);
        });
    }
}