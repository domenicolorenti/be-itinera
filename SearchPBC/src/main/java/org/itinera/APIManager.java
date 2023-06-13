package org.itinera;

import org.apache.coyote.Response;
import org.itinera.model.Business;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class APIManager {

    private static APIManager instance = null;
    private String profileURL = "http://172.20.10.4:8080";

    private RestTemplate restTemplate = new RestTemplate();

    private APIManager() {}

    public static APIManager getInstance() {
        if(instance == null)
            instance = new APIManager();
        return instance;
    }

    public List<String> getCities() {
        String url = profileURL + "/getCities";
        ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

        return response.getBody();
    }

    public List<Business> getAllBusiness() {
        String url = profileURL + "/getAllBusiness";
        ResponseEntity<List<Business>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Business>>() {});
        return response.getBody();
    }

    public HashMap<String, Float> getVotes() {
        String url = profileURL + "/getVotes";
        //ResponseEntity<HashMap<String, Float>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<HashMap<String, Float>>() {});
        HashMap<String, Float> votes = new HashMap<>();
        votes.put("test@test.it", 3.5F);
        votes.put("vavodo@vavodo.it", 2.4F);

        return votes;
    }
}
