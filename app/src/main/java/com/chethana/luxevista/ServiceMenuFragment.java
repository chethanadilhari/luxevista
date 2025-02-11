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

public class ServiceMenuFragment extends Fragment {

    RecyclerView servicesMenuRecyclerView;
    ArrayList<Service> services;
    GridLayoutManager serviceGridLayoutManager;
    ServiceMenuAdapter serviceMenuAdapter;


    public ServiceMenuFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_menu, container, false);

        servicesMenuRecyclerView = view.findViewById(R.id.servicesMenuRecyclerView);

        services = new ArrayList<>();
        services.add(new Service("Spa Treatments", "https://i.ibb.co/y7gL15G/spa-treatments.jpg"));
        services.add(new Service("Dining Reservations", "https://i.ibb.co/ycQ8ff2J/dining-reservation.jpg"));
        services.add(new Service("Poolside cabanas", "https://i.ibb.co/wFxnzM6s/poolside-cabanas.jpg"));
        services.add(new Service("Guided Beach Tours", "https://i.ibb.co/20pY2PBr/guided-beach-tours.jpg"));
        services.add(new Service("Signature Taste", "https://i.ibb.co/JwhZGPjp/signature-taste.jpg"));
        services.add(new Service("Sleep Well", "https://i.ibb.co/RGcGcXrV/sleep-well.jpg"));



       serviceGridLayoutManager = new GridLayoutManager(getActivity(), 2);

        serviceMenuAdapter = new ServiceMenuAdapter(services);

        servicesMenuRecyclerView.setLayoutManager(serviceGridLayoutManager);
        servicesMenuRecyclerView.setAdapter(serviceMenuAdapter);

        return view;
    }
}