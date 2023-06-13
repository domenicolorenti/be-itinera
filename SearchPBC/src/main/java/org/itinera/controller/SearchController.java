package org.itinera.controller;

import org.itinera.APIManager;
import org.itinera.controller.Comunication.Result;
import org.itinera.model.Business;
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

    @GetMapping("/getCities")
    public List<String> getCities() {
        return APIManager.getInstance().getCities();
    }

    //get search result
    @GetMapping("/getResult")
    public List<Result> getResult() {
        List<Result> results =  new ArrayList<>();

        List<Business> businesses = APIManager.getInstance().getAllBusiness();
        HashMap<String, Float> votes = APIManager.getInstance().getVotes();

        for (Business i: businesses){
            results.add(new Result(i.getName(), i.getEmail(), i.getAddress(), i.getDescription(), votes.get(i.getEmail())));
        }

        return results;
    }

}
