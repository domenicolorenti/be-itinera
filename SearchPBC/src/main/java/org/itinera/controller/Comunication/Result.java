package org.itinera.controller.Comunication;

public class Result {
    private String name;
    private String email;
    private String address;
    private String description;
    private float vote;

    public Result(String name, String email, String address, String description, float value) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.description = description;
        this.vote = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }
}
