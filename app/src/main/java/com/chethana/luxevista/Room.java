package com.chethana.luxevista;

public class Room {

    private String key;
    private String name;
    private String imageUrl;

    private String description;

    public Room(String key, String name, String imageUrl, String description) {
        this.key = key;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Room() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}