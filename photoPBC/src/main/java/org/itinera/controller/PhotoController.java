package org.itinera.controller;


import org.itinera.controller.communication.Protocol;
import org.itinera.model.ReviewPhoto;
import org.itinera.service.PhotoService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;


@RestController
@CrossOrigin(origins = "*")
public class PhotoController {

    @Autowired
    PhotoService photoService;


    @GetMapping("/isActive")
    public String isActive() {
        return "Photo service is active";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/putReviewPhoto")
    public JSONObject putReviewPhoto(@RequestBody JSONObject obj, HttpServletResponse response) {
        JSONObject resp = new JSONObject();

        try {
            if (photoService.savePhoto(obj, true)) {
                resp.put("Success", "Photo uploaded");
                response.setStatus(Protocol.OK);
                return resp;
            }
        } catch (UnsupportedEncodingException e) {
            resp.put("Error", "Invalid Data");
            response.setStatus(Protocol.INVALID_DATA);
        }

        resp.put("Error", "Internal Server error");
        response.setStatus(Protocol.SERVER_ERROR);
        return resp;
    }

    @GetMapping("/getReviewPhoto")
    public ReviewPhoto getReviewPhoto(int cod, HttpServletResponse response) {

        return photoService.getReviewPhoto(cod);

    }


    @SuppressWarnings("unchecked")
    @PostMapping("/putBusinessPhoto")
    public JSONObject putBusinessPhoto(@RequestBody JSONObject obj, HttpServletResponse response) {
        JSONObject resp = new JSONObject();

        try {
            if (photoService.savePhoto(obj, false)) {
                resp.put("Success", "Photo uploaded");
                response.setStatus(Protocol.OK);
                return resp;
            }
        } catch (UnsupportedEncodingException e) {
            resp.put("Error", "Invalid Data");
            response.setStatus(Protocol.INVALID_DATA);
        }

        resp.put("Error", "Internal Server error");
        response.setStatus(Protocol.SERVER_ERROR);
        return resp;
    }
}
