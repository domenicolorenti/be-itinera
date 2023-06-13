package org.itinera.persistence.JDBC;

import org.itinera.model.Review;
import org.itinera.persistence.DBConnection;
import org.itinera.persistence.dao.ReviewDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void save(Review obj) throws SQLException {
        String saveReviewQuery = "insert into reviews values(?, ?, ?, ?, ?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveReviewQuery);

        stm.setString(1, obj.getUserEmail());
        stm.setString(2, obj.getTitle());
        stm.setString(3, obj.getDescription());
        stm.setDate(4, obj.getDate());
        stm.setInt(5, obj.getVote());

        stm.execute();

        stm.close();
    }
}
