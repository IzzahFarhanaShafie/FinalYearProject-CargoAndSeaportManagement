package com.example.cargotracker.Model;

public class Users {


    String firstname, image, id, status;

    public Users(String firstname, String image, String id, String status) {
        this.firstname = firstname;
        this.image = image;
        this.id = id;
        this.status = status;
    }

    public Users() {
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}