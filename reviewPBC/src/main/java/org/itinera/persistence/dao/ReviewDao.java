package org.itinera.persistence.dao;

import org.itinera.model.Review;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class ReviewDao implements Dao<Review> {


    @Override
    public abstract List<Review> getAll() throws SQLException;

    @Override
    public abstract void save(Review obj) throws SQLException;

    public abstract List<Review> getReviewsFromEmail(String email) throws SQLException;
}
