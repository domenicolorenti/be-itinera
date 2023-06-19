package org.itinera.persistence.JDBC;

import org.itinera.model.ReviewPhoto;

import org.itinera.persistence.dao.ReviewPhotoDao;

import java.sql.SQLException;
import java.util.List;

public class ReviewPhotoDaoJDBC extends ReviewPhotoDao {

    private static ReviewPhotoDaoJDBC instance = null;

    private ReviewPhotoDaoJDBC() {}

    public static ReviewPhotoDaoJDBC getInstance() {
        if(instance == null)
            instance = new ReviewPhotoDaoJDBC();
        return instance;
    }

    @Override
    public List<ReviewPhoto> getAll() throws SQLException {

        return null;
    }

    @Override
    public void save(ReviewPhoto obj) throws SQLException {

    }

    @Override
    public byte[] getPhoto(String cod) throws SQLException {

        return new byte[0];
    }

}
