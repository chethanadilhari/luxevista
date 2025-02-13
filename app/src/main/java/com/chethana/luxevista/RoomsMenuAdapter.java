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


public class RoomsMenuAdapter extends RecyclerView.Adapter<RoomsMenuAdapter.RoomViewHolder> {

    private final ArrayList<Room> rooms;

    public RoomsMenuAdapter(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_card_sq, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.roomName.setText(room.getName());

        // Load image from URL using Glide
        Glide.with(holder.itemView.getContext())
                .load(room.getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.home_bg)) // Add placeholder image
                .into(holder.roomImage);

        holder.itemView.setOnClickListener(v -> {
            // Handle click event
             Intent intent = new Intent(holder.itemView.getContext(), RoomsActivity.class);
             intent.putExtra("roomId", room.getKey());
             intent.putExtra("roomName", room.getName());
             intent.putExtra("roomImageUrl", room.getImageUrl());
             intent.putExtra("roomDescription", room.getDescription());
             holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView roomName;
        ImageView roomImage;

        RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.roomName);
            roomImage = itemView.findViewById(R.id.roomImage);
        }
    }
}