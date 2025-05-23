package org.itinera.controller;

import org.itinera.api.APIManager;
import org.itinera.controller.communication.ReviewExchange;
import org.itinera.model.Review;
import org.itinera.service.ReviewService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.itinera.persistence.JDBC.ReviewDaoJDBC;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    ReviewService service;


    @GetMapping("/isActive")
    public String isActive() {
        return "Review service is active";
    }

    @PostMapping("/publish")
    public JSONObject publish(@RequestBody ReviewExchange obj, HttpServletResponse response) {
        JSONObject resp = new JSONObject();

        try {
            if(service.publish(obj)) {
                response.setStatus(Protocol.OK);
                resp.put("msg", "Review Saved");
            }
            else {
                response.setStatus(Protocol.SERVER_ERROR);
                resp.put("msg", "Internal server error");
            }

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
        List<Review> reviews;

        try {
            reviews = ReviewDaoJDBC.getInstance().getReviewsFromEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviews;
    }
}
