package com.chethana.luxevista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ServiceMenuFragment extends Fragment {

    RecyclerView servicesMenuRecyclerView;
    ArrayList<Service> services = new ArrayList<>();
    GridLayoutManager serviceGridLayoutManager;
    ServiceMenuAdapter serviceMenuAdapter;
    DatabaseReference servicesDbReference;


    public ServiceMenuFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_menu, container, false);

        servicesMenuRecyclerView = view.findViewById(R.id.servicesMenuRecyclerView);
        serviceGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        serviceMenuAdapter = new ServiceMenuAdapter(services);
        servicesMenuRecyclerView.setLayoutManager(serviceGridLayoutManager);
        servicesMenuRecyclerView.setAdapter(serviceMenuAdapter);

        servicesDbReference = FirebaseDatabase.getInstance().getReference("services");

        // Fetch Data from Firebase
        ValueEventListener servicesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                services.clear(); // Clear previous data to avoid duplication

                // Get Service object and use the values to update the UI
                for (DataSnapshot serviceSnapshot : snapshot.getChildren()) {
                    Service service = serviceSnapshot.getValue(Service.class);
                    if (service !=null){
                        services.add(service);
                    }

                }
                serviceMenuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Getting Post failed, log a message
                Log.w("ServicesFragment", "loadPost:onCancelled", error.toException());
            }
        };
        servicesDbReference.addValueEventListener(servicesListener);

        return view;
    }
}