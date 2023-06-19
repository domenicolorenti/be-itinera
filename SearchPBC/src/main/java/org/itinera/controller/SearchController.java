package org.itinera.controller;

import org.itinera.api.APIManager;
import org.itinera.controller.Comunication.Result;
import org.itinera.controller.Comunication.VoteDto;
import org.itinera.model.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private APIManager apiManager;


    @GetMapping("/isActive")
    public String isActive() {
        return "Profile service is active";
    }

    @GetMapping("/getCities")
    public List<String> getCities() {
        return apiManager.getCities();
    }

    //get search result
    @GetMapping("/getResult")
    public List<Result> getResult() {
        List<Result> results =  new ArrayList<>();

        List<Business> businesses = apiManager.getAllBusiness();
        Map<String, Float> votes = apiManager.getVotes();

        for (Business i: businesses){
            results.add(new Result(i.getName(), i.getEmail(), i.getAddress(), i.getDescription(), votes.get(i.getEmail())));
        }

        return results;
    }

}
