package org.itinera.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itinera.controller.comunication.Credentials;
import org.itinera.controller.comunication.Protocol;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @PostMapping("/login")
    public JSONObject doLogin(@RequestBody Credentials credentials, HttpServletResponse response) {
        JSONObject resp = new JSONObject();
        if(!credentials.username.equals("domenico") || !credentials.password.equals("domenico")) {
            response.setStatus(401);
            resp.put("msg", "Invalid Username and Password");
            return resp;
        }

        String token = credentials.username + "1234";

        response.setStatus(200);
        resp.put("key", token);
        return resp;
    }

    @GetMapping("/checkLogin")
    public JSONObject checkLogin(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resp = new JSONObject();
        String token = request.getHeader("Authorization");
        System.out.println("Token: " + token);

        if(token == null || token.isBlank()) {
            response.setStatus(Protocol.INVALID_TOKEN);
            resp.put("msg", "Invalid token");
            return resp;
        }

        response.setStatus(Protocol.OK);
        resp.put("msg", "bravo");
        return resp;

    }
}