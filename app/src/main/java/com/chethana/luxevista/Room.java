package com.chethana.luxevista;

public class Room {
    private String name;
    private String imageUrl;

    public Room(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}