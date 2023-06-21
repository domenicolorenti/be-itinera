package org.itinera.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itinera.model.BusinessPhoto;
import org.itinera.model.Photo;
import org.itinera.model.ReviewPhoto;
import org.itinera.persistence.JDBC.BusinessPhotoDaoJDBC;
import org.itinera.persistence.JDBC.ReviewPhotoDaoJDBC;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;

@Service
public class PhotoService {

    public boolean savePhoto(JSONObject obj, boolean isReview) throws UnsupportedEncodingException {

        String image = (String) obj.get("image");
        byte[] img = Base64.getDecoder().decode(image.split(",")[1].getBytes("UTF-8"));
        try {
            if (isReview)
                ReviewPhotoDaoJDBC.getInstance().save(new ReviewPhoto((String) obj.get("cod"), img));
            else
                BusinessPhotoDaoJDBC.getInstance().save(new BusinessPhoto((String) obj.get("cod"), img));
        } catch(SQLException e) {
            return false;
        }

        return true;
    }

    public Photo getPhoto(String key, boolean isReview) {
        return null;
    }

}
