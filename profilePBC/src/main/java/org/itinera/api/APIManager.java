package org.itinera.api;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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

    @Value("${photo.url}")
    private String photourl;


    private final RestTemplate restTemplate = new RestTemplate();

    public float getBusinessVote(String email) {
        String url = rankingurl + "/getVote/?email=" + email;

        ResponseEntity<Float> response = restTemplate.exchange(url, HttpMethod.GET, null, Float.class);

        return response.getBody();
    }

    public String testToSearch() {
        String url = searchurl + "/isActive";

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        return response.getBody();
    }

    public boolean putBusinessPhoto(String email, String image) {
        String url = photourl + "/putBusinessPhoto";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crea un oggetto JSON per il corpo della richiesta
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", email);
        requestBody.put("image", image);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        String response = responseEntity.getBody();

        return responseEntity.getStatusCode().is2xxSuccessful();
    }
}
