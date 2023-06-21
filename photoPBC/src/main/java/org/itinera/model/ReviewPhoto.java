package org.itinera.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewPhoto implements Photo{
    private String review;
    private byte[] photo;

    public ReviewPhoto() {}

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

    public static ReviewPhoto parseFromDB(ResultSet rs) throws SQLException, IllegalArgumentException, NullPointerException {
        ReviewPhoto review = new ReviewPhoto();
        review.setReview(rs.getString("review"));
        review.setPhoto(rs.getBytes("photo"));

        return review;
    }
}
