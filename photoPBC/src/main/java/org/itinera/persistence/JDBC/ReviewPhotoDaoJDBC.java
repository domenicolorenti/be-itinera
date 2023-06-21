package org.itinera.persistence.JDBC;

import org.itinera.model.ReviewPhoto;

import org.itinera.persistence.DBConnection;
import org.itinera.persistence.dao.ReviewPhotoDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String saveQuery = "insert into reviewPhoto values(default, ?, ?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveQuery);

        stm.setString(1, obj.getReview());
        stm.setBytes(2, obj.getPhoto());

        stm.execute();

        stm.close();
    }

    @Override
    public ReviewPhoto getPhoto(String cod) throws SQLException {

        String getPhotoQuery = "SELECT * FROM reviewPhoto where review=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getPhotoQuery);
        stm.setString(1, cod);
        ResultSet rs = stm.executeQuery();

        ReviewPhoto reviewPhoto = null;
        if(rs.next())
            reviewPhoto = ReviewPhoto.parseFromDB(rs);

        stm.close();
        rs.close();
        return reviewPhoto;
    }

}
