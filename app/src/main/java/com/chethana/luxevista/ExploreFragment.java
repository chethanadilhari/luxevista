package com.chethana.luxevista;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class ExploreFragment extends Fragment {

    RecyclerView roomsRecyclerView;
    RecyclerView locationRecyclerView;
    ArrayList<Room> rooms = new ArrayList<>();  // Initialize the list
    ArrayList<Location> locations = new ArrayList<>();  // Initialize the list
    LinearLayoutManager roomsLinearLayoutManager;
    LinearLayoutManager locationsLinearLayoutManager;
    RoomsAdapter roomsAdapter;
    LocationAdapter locationAdapter;
    DatabaseReference roomsDbReference, locationDbReference;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        roomsRecyclerView = view.findViewById(R.id.roomsRecyclerView);
        locationRecyclerView = view.findViewById(R.id.locationRecyclerView);
        roomsLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        locationsLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        // Initialize adapters after lists are initialized
        roomsAdapter = new RoomsAdapter(rooms);
        locationAdapter = new LocationAdapter(locations);

        roomsRecyclerView.setLayoutManager(roomsLinearLayoutManager);
        locationRecyclerView.setLayoutManager(locationsLinearLayoutManager);
        roomsRecyclerView.setAdapter(roomsAdapter);
        locationRecyclerView.setAdapter(locationAdapter);

        // Initialize Firebase Database reference
        roomsDbReference = FirebaseDatabase.getInstance().getReference("rooms");
        locationDbReference = FirebaseDatabase.getInstance().getReference("locations");

        // Fetch Data from Firebase
        ValueEventListener roomsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                rooms.clear(); // Clear previous data to avoid duplication
                // Get Room object and use the values to update the UI
                for (DataSnapshot roomSnapshot : snapshot.getChildren()) {
                    Room room = roomSnapshot.getValue(Room.class);
                    if(room != null) {
                        rooms.add(room);
                    }
                }
                roomsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Getting Room data failed, log a message
                Log.w(TAG, "loadRoom:onCancelled", error.toException());
            }
        };
        roomsDbReference.addValueEventListener(roomsListener);

        ValueEventListener locationListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                locations.clear(); // Clear previous data to avoid duplication
                // Get Location object and use the values to update the UI
                for (DataSnapshot locationSnapshot : snapshot.getChildren()) {
                    Location location = locationSnapshot.getValue(Location.class);
                    if(location != null) {
                        locations.add(location);
                    }
                }
                locationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Getting Location data failed, log a message
                Log.w(TAG, "loadLocation:onCancelled", error.toException());
            }
        };
        locationDbReference.addValueEventListener(locationListener);

        return view;
    }
}