package com.chethana.luxevista;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class RoomsActivity extends AppCompatActivity {

    com.google.android.material.imageview.ShapeableImageView roomImage;
    TextView roomName;
    TextView roomDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        roomImage = findViewById(R.id.roomImage);
        roomName = findViewById(R.id.roomName);
        roomDescription = findViewById(R.id.roomDescription);

        // Get room details from intent
        String roomNameText = getIntent().getStringExtra("roomName");
        String roomImageUrl = getIntent().getStringExtra("roomImageUrl");
        String roomDescriptionText = getIntent().getStringExtra("roomDescription");

        // Set Values
        roomName.setText(roomNameText);
        roomDescription.setText(roomDescriptionText);

        Glide.with(this)
            .load(roomImageUrl)
            .into(roomImage);
    }
}