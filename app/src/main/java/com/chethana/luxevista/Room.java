package com.chethana.luxevista;

public class Room {

    private String key;
    private String name;
    private String imageUrl;

    private String description;

    private Long price;

    private int maxPax;

    public Room(String key, String name, String imageUrl, String description, Long price, int maxPax) {
        this.key = key;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.maxPax=maxPax;
    }

    public Room() {
    }

    public String getKey() {
        return key;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
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