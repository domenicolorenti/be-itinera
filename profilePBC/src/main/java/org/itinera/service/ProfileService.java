package org.itinera.service;

import org.itinera.api.APIManager;
import org.itinera.persistence.JDBC.BusinessDaoJDBC;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ProfileService {

    @Autowired
    APIManager api;

    public boolean businessModify(JSONObject obj) {
        String desc = (String) obj.get("description");
        String email = (String) obj.get("email");

        try {
            BusinessDaoJDBC.getInstance().addDescription(email ,desc);
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        return api.putBusinessPhoto(email, (String) obj.get("image"));

    }
}
