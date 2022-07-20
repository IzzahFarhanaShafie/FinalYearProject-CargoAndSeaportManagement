package com.example.cargotracker;

public class announcement {

    private String imageUrl, imagename;

    public announcement(String imageUrl, String imagename) {
        this.imageUrl = imageUrl;
        this.imagename = imagename;
    }

    public announcement() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
}
