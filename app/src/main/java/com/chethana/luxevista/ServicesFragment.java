package com.chethana.luxevista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServicesFragment extends Fragment {

    RecyclerView serviceRecyclerView;
    ArrayList<Service> services;
    LinearLayoutManager serviceLinearLayoutManager;
    ServiceAdapter serviceAdapter;
    DatabaseReference servicesDbReference;

    public ServicesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        serviceRecyclerView = view.findViewById(R.id.servicesRecyclerView);
        services = new ArrayList<>();
        serviceLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        serviceAdapter = new ServiceAdapter(services);
        serviceRecyclerView.setLayoutManager(serviceLinearLayoutManager);
        serviceRecyclerView.setAdapter(serviceAdapter);

        // Initialize Firebase Database reference
        servicesDbReference = FirebaseDatabase.getInstance().getReference("services");

        // Fetch Data from Firebase

        ValueEventListener servicesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                services.clear(); // Clear previous data to avoid duplication

                // Get Service object and use the values to update the UI
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Service service = itemSnapshot.getValue(Service.class);
                    if (service != null) {
                        services.add(service);
                    }
                }
                serviceAdapter.notifyDataSetChanged(); // Update RecyclerView after fetching data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to load services"+databaseError, Toast.LENGTH_SHORT).show();
            }
        };
        servicesDbReference.addValueEventListener(servicesListener);

//


        return view;
    }
}