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

public class RoomsMenuFragment extends Fragment {

    RecyclerView roomsMenuRecyclerView;
    ArrayList<Room> rooms = new ArrayList<>();
    GridLayoutManager roomGridLayoutManager;
    RoomsMenuAdapter roomsMenuAdapter;
    DatabaseReference roomsDbReference;


    public RoomsMenuFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms_menu, container, false);

        roomsMenuRecyclerView = view.findViewById(R.id.roomsMenuRecyclerView);
        roomGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        roomsMenuAdapter = new RoomsMenuAdapter(rooms);
        roomsMenuRecyclerView.setLayoutManager(roomGridLayoutManager);
        roomsMenuRecyclerView.setAdapter(roomsMenuAdapter);

        roomsDbReference = FirebaseDatabase.getInstance().getReference("rooms");

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
                roomsMenuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Getting Room failed, log a message
                Log.w("RoomsMenuFragment", "loadRoom:onCancelled", error.toException());
            }
        };
        roomsDbReference.addValueEventListener(roomsListener);

        return view;
    }
}