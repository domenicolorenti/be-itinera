package org.itinera.controller;

import org.itinera.api.APIManager;
import org.itinera.model.Review;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.itinera.persistence.JDBC.ReviewDaoJDBC;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    APIManager apiManager;


    @GetMapping("/isActive")
    public String isActive() {
        return "Review service is active";
    }

    @PostMapping("/publish")
    public JSONObject doLogin(@RequestBody Review review, HttpServletResponse response) {
        JSONObject resp = new JSONObject();

        try {
            ReviewDaoJDBC.getInstance().save(review);
            apiManager.addVote(review.getBusinessEmail(), review.getVote());
            response.setStatus(Protocol.OK);
            resp.put("msg", "Review Saved");

            return resp;
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Protocol.SERVER_ERROR);
            resp.put("msg", "Internal server error");

            return resp;
        } catch(IllegalArgumentException | NullPointerException e2) {
            e2.printStackTrace();
            response.setStatus(Protocol.INVALID_DATA);
            resp.put("msg", "The provided email are not valid");

            return resp;
        }
    }

    @GetMapping("/getBusinessReviews")
    public List<Review> getResult(String email) {
        List<Review> reviews =  new ArrayList<>();

        try {
            reviews = ReviewDaoJDBC.getInstance().getReviewsFromEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviews;
    }
}
