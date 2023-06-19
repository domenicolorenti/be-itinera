package org.itinera.persistence.dao;


import org.itinera.model.ReviewPhoto;

import java.sql.SQLException;
import java.util.List;

public abstract class ReviewPhotoDao implements Dao<ReviewPhoto> {


    @Override
    public abstract List<ReviewPhoto> getAll() throws SQLException;

    @Override
    public abstract void save(ReviewPhoto obj) throws SQLException;

    @Override
    public abstract byte[] getPhoto(String cod) throws SQLException;

}
