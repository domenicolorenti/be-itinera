package org.itinera.controller;

import org.itinera.controller.comunication.Protocol;
import org.itinera.model.Business;
import org.itinera.persistence.JDBC.BusinessDaoJDBC;
import org.itinera.persistence.domain.Email;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
public class FeaturesController {

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
            user.setVote(2.4F);
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
}
