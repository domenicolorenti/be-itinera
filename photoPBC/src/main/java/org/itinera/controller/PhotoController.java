package org.itinera.controller;


import org.itinera.controller.comunication.Protocol
import org.itinera.model.ReviewPhoto;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


@RestController
@CrossOrigin(origins = "*")
public class PhotoController {

    @GetMapping("/isActive")
    public String isActive() {
        return "Photo service is active";
    }

    @GetMapping("/reviewPhoto")
    public void putReviewPhoto(@RequestBody JSONObject obj, HttpServletResponse response) {
        try {
            String avatar = (String) obj.get("image");
            byte[] img = Base64.getDecoder().decode(avatar.split(",")[1].getBytes("UTF-8"));
            ReviewPhoto imageToSave = new ReviewPhoto((String) obj.get("cod"), img);

            response.setStatus(Protocol.OK);
        } catch (UnsupportedEncodingException e) {
            response.setStatus(Protocol.INVALID_DATA);
        }
    }
}
