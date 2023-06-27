package org.itinera.api;

import org.itinera.model.Business;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class APIManager {

    @Value("${profile.url}")
    private String profileurl;

    @Value("${review.url}")
    private String reviewurl;

    @Value("${ranking.url}")
    private String rankingurl;

    private RestTemplate restTemplate = new RestTemplate();


    public List<String> getCities() {
        String url = profileurl + "/getCities";

         ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

        return response.getBody();
    }

    public List<Business> getAllBusiness() {
        String url = profileurl + "/getAllBusiness";

        ResponseEntity<List<Business>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Business>>() {});
        return response.getBody();
    }

    public Map<String, Float> getVotes() {
        String url = rankingurl + "/getAllVotes";
        ResponseEntity<Map<String, Map<String, Float>>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, Map<String, Float>>>() {}
        );

        Map<String, Map<String, Float>> responseBody = response.getBody();
        
        return responseBody != null ? responseBody.get("votes") : null;
    }


}
