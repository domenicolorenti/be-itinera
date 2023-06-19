package org.itinera.model;

public class ReviewPhoto {
    private String review;
    private byte[] photo;

    public ReviewPhoto(String review, byte[] photo) {
        this.review = review;
        this.photo = photo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
