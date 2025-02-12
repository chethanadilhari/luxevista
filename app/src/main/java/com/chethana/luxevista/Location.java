package com.chethana.luxevista;

public class Location {

    private String name;
    private String imageUrl;

    public Location(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
