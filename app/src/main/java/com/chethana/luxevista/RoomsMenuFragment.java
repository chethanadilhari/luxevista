package com.chethana.luxevista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RoomsMenuFragment extends Fragment {

    RecyclerView roomsMenuRecyclerView;
    ArrayList<Room> rooms;
    GridLayoutManager roomGridLayoutManager;
    RoomsMenuAdapter roomsMenuAdapter;


    public RoomsMenuFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rooms_menu, container, false);

        roomsMenuRecyclerView = view.findViewById(R.id.roomsMenuRecyclerView);

        rooms = new ArrayList<>();
        rooms.add(new Room("Standard Room", "https://i.ibb.co/sp9cpTfb/standard-room.jpg"));
        rooms.add(new Room("Deluxe Room", "https://i.ibb.co/ch8yngRN/deluxe-room.jpg"));
        rooms.add(new Room("Family Room", "https://i.ibb.co/99GtJH0S/family-room.jpg"));
        rooms.add(new Room("Room with Sea View", "https://i.ibb.co/Qw4g5XN/sea-view-room.jpg"));
        rooms.add(new Room("Room with Garden View", "https://i.ibb.co/MkxBbJ7z/garden-view-room.jpg"));
        rooms.add(new Room("Room with Pool View", "https://i.ibb.co/Csb5GfsR/pool-view-room.jpg"));


        roomGridLayoutManager = new GridLayoutManager(getActivity(), 2);

        roomsMenuAdapter = new RoomsMenuAdapter(rooms);

        roomsMenuRecyclerView.setLayoutManager(roomGridLayoutManager);
        roomsMenuRecyclerView.setAdapter(roomsMenuAdapter);

        return view;
    }
}