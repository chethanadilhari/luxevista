package com.chethana.luxevista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {

    RecyclerView roomsRecyclerView;
    RecyclerView locationRecyclerView;
    ArrayList<Room> rooms;
    ArrayList<Location> locations;
    LinearLayoutManager roomsLinearLayoutManager;
    LinearLayoutManager locationsLinearLayoutManager;
    RoomsAdapter roomsAdapter;
    LocationAdapter locationAdapter;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        roomsRecyclerView = view.findViewById(R.id.roomsRecyclerView);
        locationRecyclerView = view.findViewById(R.id.locationRecyclerView);

        rooms = new ArrayList<>();
        rooms.add(new Room("Standard Room", "https://i.ibb.co/sp9cpTfb/standard-room.jpg"));
        rooms.add(new Room("Deluxe Room", "https://i.ibb.co/ch8yngRN/deluxe-room.jpg"));
        rooms.add(new Room("Family Room", "https://i.ibb.co/99GtJH0S/family-room.jpg"));
        rooms.add(new Room("Room with Sea View", "https://i.ibb.co/Qw4g5XN/sea-view-room.jpg"));
        rooms.add(new Room("Room with Garden View", "https://i.ibb.co/MkxBbJ7z/garden-view-room.jpg"));
        rooms.add(new Room("Room with Pool View", "https://i.ibb.co/Csb5GfsR/pool-view-room.jpg"));

        locations = new ArrayList<>();
        locations.add(new Location("Tirta Gangga Water Palace", "https://i.ibb.co/39bTGmVF/tirta-gangga-water-palace-bali.jpg"));
        locations.add(new Location("Typical Ancient Architecture Island ", "https://i.ibb.co/YFhKPbNN/typical-ancient-architecture-island-bali.jpg"));
        locations.add(new Location("Kelingking Beach Nusa Penida Island", "https://i.ibb.co/fdsjv7YV/kelingking-beach-nusa-penida-island-bali.jpg"));
        locations.add(new Location("Karangasem Water Temple Palace", "https://i.ibb.co/ZzNQcJYT/karangasem-water-temple-palace-bali.jpg"));
        locations.add(new Location("Houses Cozy City", "https://i.ibb.co/8D6YKKvc/houses-cozy-city.jpg"));
        locations.add(new Location("Aigua Blava Bay", "https://i.ibb.co/mChMJkm0/aigua-blava-bay-bali.jpg"));

        roomsLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        locationsLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        roomsAdapter = new RoomsAdapter(rooms);
        locationAdapter = new LocationAdapter(locations);

        roomsRecyclerView.setLayoutManager(roomsLinearLayoutManager);
        locationRecyclerView.setLayoutManager(locationsLinearLayoutManager);

        roomsRecyclerView.setAdapter(roomsAdapter);
        locationRecyclerView.setAdapter(locationAdapter);

        return view;
    }
}