package org.itinera.controller;

import javax.servlet.http.HttpServletResponse;

import org.itinera.controller.comunication.Credentials;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("/login")
    public JSONObject doLogin(@RequestBody Credentials credentials, HttpServletResponse response) {
        JSONObject resp = new JSONObject();
        if(credentials.username.equals("domenico") && credentials.password.equals("domenico")) {
            System.out.println("arrivato");
            response.setStatus(200);
            resp.put("key", 2293800);
            return resp;
        }
        response.setStatus(500);
        return resp;
    }
}