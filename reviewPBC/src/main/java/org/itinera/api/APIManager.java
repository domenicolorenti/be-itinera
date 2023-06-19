package org.itinera.api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class APIManager {

    @Value("${profile.url}")
    private String profileurl;

    @Value("${search.url}")
    private String searchurl;

    @Value("${ranking.url}")
    private String rankingurl;


    private final RestTemplate restTemplate = new RestTemplate();


    public String addVote(String email, float vote) {
        String url = rankingurl + "/addVote/?email=" + email + "&vote=" + vote;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});

        return response.getBody();
    }
}
