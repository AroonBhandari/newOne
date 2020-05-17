package com.example.kotha;

import android.widget.ImageView;

public class room {
    int id, noRooms;
    String location,firstName,lastName,description;
    int budget;
    byte[] imageView;

    public room(int id, String location, String firstName, String lastName, String description, int noRooms, int budget,byte[] imageView) {
        this.id = id;
        this.location = location;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.noRooms = noRooms;
        this.budget = budget;
        this.imageView=imageView;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageView(byte[] imageView) {
        this.imageView = imageView;
    }

    public int getId() {
        return id;
    }

    public byte[] getImageView() {
        return imageView;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNoRooms(int noRooms) {
        this.noRooms = noRooms;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getLocation() {
        return location;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDescription() {
        return description;
    }

    public int getNoRooms() {
        return noRooms;
    }

    public int getBudget() {
        return budget;
    }
}
