package org.itinera.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BusinessPhoto implements Photo{
    private String email;
    private byte[] photo;


    public BusinessPhoto() {}

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

    public static BusinessPhoto parseFromDB(ResultSet rs) throws SQLException, IllegalArgumentException, NullPointerException {
        BusinessPhoto review = new BusinessPhoto();
        review.setEmail(rs.getString("email"));
        review.setPhoto(rs.getBytes("photo"));

        return review;
    }
}
