package org.itinera.persistence.JDBC;

import org.itinera.model.Review;
import org.itinera.persistence.DBConnection;
import org.itinera.persistence.dao.ReviewDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoJDBC extends ReviewDao {

    private static ReviewDaoJDBC instance = null;

    private ReviewDaoJDBC() {}

    public static ReviewDaoJDBC getInstance() {
        if(instance == null)
            instance = new ReviewDaoJDBC();
        return instance;
    }

    @Override
    public List<Review> getAll() throws SQLException {
        String getAllQuery = "SELECT * FROM reviews";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getAllQuery);
        ResultSet rs = stm.executeQuery();

        ArrayList<Review> reviews = new ArrayList<>();
        while(rs.next()) {
            Review review = Review.parseFromDB(rs);
            reviews.add(review);
        }

        stm.close();
        rs.close();
        return reviews;
    }

    @Override
    public int save(Review obj) throws SQLException {
        String saveReviewQuery = "INSERT INTO reviews VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING cod";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveReviewQuery);

        stm.setString(1, obj.getBusinessEmail());
        stm.setString(2, obj.getTitle());
        stm.setString(3, obj.getDescription());
        stm.setInt(4, obj.getVote());
        stm.setString(5, obj.getUserName());
        stm.setDate(6, obj.getDate());

        ResultSet rs = stm.executeQuery();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt("cod");
        }

        stm.close();
        return generatedKey;
    }

    @Override
    public List<Review> getReviewsFromEmail(String email) throws SQLException {
        String getAllQuery = "SELECT * FROM reviews where business=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getAllQuery);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();

        ArrayList<Review> reviews = new ArrayList<>();
        while(rs.next()) {
            rs.getString("business");
            Review review = Review.parseFromDB(rs);
            reviews.add(review);
        }

        stm.close();
        rs.close();
        return reviews;
    }

}
