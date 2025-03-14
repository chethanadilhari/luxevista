package com.chethana.luxevista;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private final ArrayList<Service> services;

    public ServiceAdapter(ArrayList<Service> services) {
        this.services = services;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_card, parent, false);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service service = services.get(position);
        holder.serviceName.setText(service.getName());

        // Load image from URL using Glide
        Glide.with(holder.itemView.getContext())
                .load(service.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.home_bg)) // Add placeholder image
                .into(holder.serviceImage);

        holder.itemView.setOnClickListener(v -> {
            // Open SubServiceDetails fragment
            Intent intent = new Intent(holder.itemView.getContext(), ServiceActivity.class);
            intent.putExtra("serviceId", service.getKey());
            intent.putExtra("serviceName", service.getName());
            intent.putExtra("serviceImageUrl", service.getImageUrl());
            intent.putExtra("serviceDescription", service.getDescription());

            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    static class ServiceViewHolder extends RecyclerView.ViewHolder {
        TextView serviceName;
        ImageView serviceImage;

        TextView serviceDescription;

        ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.serviceName);
            serviceImage = itemView.findViewById(R.id.serviceImage);
            serviceDescription = itemView.findViewById(R.id.serviceDescription);
        }
    }
}