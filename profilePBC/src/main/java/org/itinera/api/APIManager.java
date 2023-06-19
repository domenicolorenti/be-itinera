package org.itinera.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIManager {

    @Value("${search.url}")
    private String searchurl;

    @Value("${review.url}")
    private String reviewurl;

    @Value("${ranking.url}")
    private String rankingurl;

    private final RestTemplate restTemplate = new RestTemplate();

    public float getBusinessVote(String email) {
        String url = rankingurl + "/getVote/?email=" + email;

        ResponseEntity<Float> response = restTemplate.exchange(url, HttpMethod.GET, null, Float.class);

        return response.getBody();
    }
}
