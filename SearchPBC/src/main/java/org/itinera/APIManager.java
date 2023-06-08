package org.itinera;

import org.apache.coyote.Response;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class APIManager {

    private static APIManager instance = null;

    private RestTemplate restTemplate = new RestTemplate();

    private APIManager() {}

    public static APIManager getInstance() {
        if(instance == null)
            instance = new APIManager();
        return instance;
    }

    public List<String> getCities() {
        String url = "http://192.168.1.151:8080/getCities";

        ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});

        return response.getBody();
    }

}
