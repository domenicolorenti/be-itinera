package org.itinera.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Review {
    private String userEmail;
    private String title;
    private String description;
    private Date date;
    private int vote;

    public Review() {}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date= date;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public static Review parseFromDB(ResultSet rs) throws SQLException, IllegalArgumentException, NullPointerException {
        Review review = new Review();
        review.setUserEmail(rs.getString("userEmail"));
        review.setTitle(rs.getString("title"));
        review.setDescription(rs.getString("description"));
        review.setVote(rs.getInt("vote"));
        review.setDate(rs.getDate("ddate"));

        return review;
    }
}
