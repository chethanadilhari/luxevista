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
    ArrayList<Room> rooms;
    LinearLayoutManager linearLayoutManager;
    RoomsAdapter roomsAdapter;

    public ExploreFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        roomsRecyclerView = view.findViewById(R.id.roomsRecyclerView);

        rooms = new ArrayList<>();
        rooms.add(new Room("Room 1", "https://i.ibb.co/ch8yngRN/deluxe-room.jpg"));
rooms.add(new Room("Room 2", "https://i.ibb.co/99GtJH0S/family-room.jpg"));
rooms.add(new Room("Room 3", "https://i.ibb.co/MkxBbJ7z/garden-view-room.jpg"));

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        roomsAdapter = new RoomsAdapter(rooms);
        roomsRecyclerView.setLayoutManager(linearLayoutManager);
        roomsRecyclerView.setAdapter(roomsAdapter);

        return view;
    }
}