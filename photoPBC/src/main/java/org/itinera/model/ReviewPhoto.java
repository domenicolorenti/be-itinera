package org.itinera.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewPhoto implements Photo{
    private int review;
    private byte[] photo;

    public ReviewPhoto() {}

    public ReviewPhoto(int review, byte[] photo) {
        this.review = review;
        this.photo = photo;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
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
        review.setReview(rs.getInt("review"));
        review.setPhoto(rs.getBytes("photo"));

        return review;
    }
}
