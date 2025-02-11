package com.chethana.luxevista;

public class Service {

    private String name;
    private String imageUrl;

    public Service(String name, String imageUrl) {
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
