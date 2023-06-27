package org.itinera.api;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    @Value("${photo.url}")
    private String photourl;


    private final RestTemplate restTemplate = new RestTemplate();


    public String addVote(String email, float vote) {
        String url = rankingurl + "/addVote/?email=" + email + "&vote=" + vote;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});

        return response.getBody();
    }

    public boolean addPhoto(int cod, String image) {
        String url = photourl + "/putReviewPhoto";

        System.out.println("image: " + image);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crea un oggetto JSON per il corpo della richiesta
        JSONObject requestBody = new JSONObject();
        requestBody.put("cod", cod);
        requestBody.put("image", image);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String response = responseEntity.getBody();

        return responseEntity.getStatusCode().is2xxSuccessful();
    }

}
