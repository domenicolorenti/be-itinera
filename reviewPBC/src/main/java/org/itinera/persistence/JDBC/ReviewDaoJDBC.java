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
        String saveReviewQuery = "insert into reviews values(default, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveReviewQuery);

        stm.setString(1, obj.getBusinessEmail());
        stm.setString(2, obj.getTitle());
        stm.setString(3, obj.getDescription());
        stm.setInt(4, obj.getVote());
        stm.setString(5, obj.getUserName());
        stm.setDate(6, obj.getDate());

        stm.execute();

        stm.close();
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

    public float getBusinessValue(String email) throws SQLException {
        String getVoteQuery = "SELECT vote FROM reviews where business=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getVoteQuery);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();

        float sum = 0F;
        int cont = 0;
        while(rs.next()) {
            sum += rs.getInt("vote");
            cont++;
        }

        stm.close();
        rs.close();

        if(cont == 0)
            return 0;

        return sum/cont;
    }
}
