package org.itinera.service;

import org.itinera.api.APIManager;
import org.itinera.controller.Protocol;
import org.itinera.controller.communication.ReviewExchange;
import org.itinera.model.Review;
import org.itinera.persistence.JDBC.ReviewDaoJDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ReviewService {

    @Autowired
    APIManager apiManager;

    public boolean publish(ReviewExchange review) throws NullPointerException, IllegalArgumentException {
        try {
            int cod = ReviewDaoJDBC.getInstance().save(Review.parseFromExchange(review));
            apiManager.addVote(review.getBusinessEmail(), review.getVote());
            apiManager.addPhoto(cod, review.getImage());
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
