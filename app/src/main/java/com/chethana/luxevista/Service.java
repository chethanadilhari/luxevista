package com.chethana.luxevista;

public class Service {

    private String key;
    private String name;
    private String imageUrl;
    private String description;

    public Service() {
    }

    public Service(String key, String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.key = key;
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
