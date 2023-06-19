package org.itinera.model;

public class BusinessPhoto {
    private String email;
    private byte[] photo;

    public BusinessPhoto(String review, byte[] photo) {
        this.email = review;
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
