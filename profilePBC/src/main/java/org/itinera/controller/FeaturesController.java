package org.itinera.controller;

import org.itinera.api.APIManager;
import org.itinera.controller.communication.Protocol;
import org.itinera.model.Business;
import org.itinera.persistence.JDBC.BusinessDaoJDBC;
import org.itinera.persistence.domain.Email;
import org.itinera.service.ProfileService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
public class FeaturesController {

    @Autowired
    private APIManager apiManager;

    @Autowired
    private ProfileService service;

    @GetMapping("/isActive")
    public String isActive() {
        return "Profile service is active";
    }

    @GetMapping("/testToSearch")
    public String testToSearch() {
        return apiManager.testToSearch();
    }

    @GetMapping("/getBusiness")
    public JSONObject getBusiness(String email, HttpServletResponse response) {
        Business user = null;
        JSONObject resp = new JSONObject();

        try {
            user = BusinessDaoJDBC.getInstance().findByEmail(new Email(email));

            if(user == null) {
                response.setStatus(Protocol.WRONG_CREDENTIALS);
                resp.put("msg", "The email is not valid");

                return resp;
            }

            //altrimenti, restituisco 200 e l'oggetto user
            user.setVote(apiManager.getBusinessVote(user.getEmail()));
            response.setStatus(Protocol.OK);
            resp.put("user", user);

            return resp;

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Protocol.SERVER_ERROR);
            resp.put("msg", "Internal server error");

            return resp;
        }
    }

    @PostMapping("/businessModify")
    public JSONObject businessModify(@RequestBody  JSONObject obj, HttpServletResponse response) {
        JSONObject resp = new JSONObject();


        if(service.businessModify(obj)) {
            response.setStatus(Protocol.OK);
            resp.put("msg", "profile updated");
            return resp;
        }

        response.setStatus(Protocol.SERVER_ERROR);
        resp.put("error", "Internal error");
        return resp;
    }
}
