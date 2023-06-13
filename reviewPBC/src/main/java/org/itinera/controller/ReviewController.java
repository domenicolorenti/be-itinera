package org.itinera.controller;

import org.itinera.model.Review;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.itinera.persistence.JDBC.ReviewDaoJDBC;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;


@RestController
@CrossOrigin(origins = "*")
public class ReviewController {


    @PostMapping("/publish")
    public JSONObject doLogin(@RequestBody Review review, HttpServletResponse response) {
        JSONObject resp = new JSONObject();


        try {

            ReviewDaoJDBC.getInstance().save(review);
            response.setStatus(Protocol.OK);
            resp.put("msg", "Account created");

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
}
