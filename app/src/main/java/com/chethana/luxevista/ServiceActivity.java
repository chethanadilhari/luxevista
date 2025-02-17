package com.chethana.luxevista;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    TextView serviceName;
    TextView serviceDescription;

    DatabaseReference reference;

    RecyclerView subServiceRecyclerView;
    ArrayList<SubService> subServices;
    LinearLayoutManager subServiceLinearLayoutManager;
    SubServiceAdapter subServiceAdapter;

    com.google.android.material.imageview.ShapeableImageView serviceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceName = findViewById(R.id.serviceName);
        serviceDescription = findViewById(R.id.serviceDescription);
        serviceImage = findViewById(R.id.serviceImage);

        // Get service details from intent
        String serviceId = getIntent().getStringExtra("serviceId");
        String serviceNameText = getIntent().getStringExtra("serviceName");
        String serviceImageUrl = getIntent().getStringExtra("serviceImageUrl");
        String serviceDescriptionText = getIntent().getStringExtra("serviceDescription");

        // Set Values
        serviceName.setText(serviceNameText);
        serviceDescription.setText(serviceDescriptionText);

        ImageView backButton = findViewById(R.id.backIcon);
        backButton.setOnClickListener(v -> finish());

        Glide.with(this)
                .load(serviceImageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.home_bg)) // Add placeholder image
                .into(serviceImage);

        subServiceRecyclerView = findViewById(R.id.subServiceRecyclerView);
        subServices = new ArrayList<>();
        subServiceLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        subServiceAdapter = new SubServiceAdapter(subServices);
        subServiceRecyclerView.setLayoutManager(subServiceLinearLayoutManager);
        subServiceRecyclerView.setAdapter(subServiceAdapter);

        reference = FirebaseDatabase.getInstance().getReference("services").child(serviceId).child("child");

        // Fetch Data from Firebase

        ValueEventListener subServicesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                subServices.clear(); // Clear previous roomdata to avoid duplication

                // Get Service object and use the values to update the UI
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    SubService subService = itemSnapshot.getValue(SubService.class);
                    if (subService != null) {
                        subServices.add(subService);
                    }
                }
                subServiceAdapter.notifyDataSetChanged(); // Update RecyclerView after fetching data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ServiceActivity.this, "Failed to load services"+databaseError, Toast.LENGTH_SHORT).show();
            }
        };
        reference.addValueEventListener(subServicesListener);
    }
}