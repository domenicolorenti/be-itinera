package org.itinera.controller;

import org.itinera.model.Business;
import org.itinera.persistence.JDBC.BusinessDaoJDBC;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class APIController {


    @GetMapping("/getCities")
    public List<String> getCities() {

        List<String> cities = null;

        try {
            cities =  BusinessDaoJDBC.getInstance().getCities();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }

    @GetMapping("/getAllBusiness")
    public List<Business> getAllBusiness() {
        List<Business> businesses = null;

        try {
            businesses = BusinessDaoJDBC.getInstance().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return businesses;
    }

    @GetMapping("/test")
    @CrossOrigin(origins = "*")
    public String test() {
        return "Test is succesfully";
    }
}
