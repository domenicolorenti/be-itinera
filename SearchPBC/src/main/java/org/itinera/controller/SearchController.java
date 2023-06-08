package org.itinera.controller;

import org.itinera.APIManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SearchController {

    @GetMapping("/getCities")
    public List<String> getCities() {
        return null;
    }

}
