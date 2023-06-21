package org.itinera.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.itinera.Utils;
import org.itinera.controller.communication.BusinessRegistrationFields;
import org.itinera.controller.communication.Credentials;
import org.itinera.controller.communication.Protocol;
import org.itinera.controller.communication.RegistrationFields;
import org.itinera.model.Business;
import org.itinera.model.GeneralUser;
import org.itinera.model.User;
import org.itinera.persistence.JDBC.BusinessDaoJDBC;
import org.itinera.persistence.JDBC.UserDaoJDBC;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;
import org.itinera.persistence.domain.Username;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @SuppressWarnings("unchecked")
    @PostMapping("/login")
    public JSONObject doLogin(@RequestBody Credentials credentials, HttpServletResponse response) {
        User user = null;
        JSONObject resp = new JSONObject();

        try {
            user = UserDaoJDBC.getInstance().checkCredentials(new Username(credentials.username), new Password(credentials.password));

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Protocol.SERVER_ERROR);
            resp.put("msg", "Internal server error");

            return resp;
        } catch (IllegalArgumentException | NullPointerException e2) {
            e2.printStackTrace();
            response.setStatus(Protocol.INVALID_CREDENTIALS);
            resp.put("msg", "The provided credentials are not valid");

            return resp;
        }

        if(user == null) {
            response.setStatus(Protocol.WRONG_CREDENTIALS);
            resp.put("msg", "Invalid combination of username and password");

            return resp;
        }

        String token = "";

        try {
            token = UserDaoJDBC.getInstance().getToken(credentials.username);

            //se il token è vuoto, ne genero uno nuovo
            if(token== null || token.isBlank()) {
                String newToken = Utils.generateNewToken();
                UserDaoJDBC.getInstance().saveToken(credentials.username, newToken);

                token = newToken;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(Protocol.SERVER_ERROR);
            resp.put("msg", "Internal server error");

            return resp;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(Protocol.OK);
        resp.put("key", token);

        return resp;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/checkLogin")
    public JSONObject checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        JSONObject resp = new JSONObject();

        if(token != null && !token.isBlank()) {
            try {
                //cerco l'utente che ha quel token di accesso
                GeneralUser user = UserDaoJDBC.getInstance().findByToken(token);
                if(user == null)
                    user = BusinessDaoJDBC.getInstance().findByToken(token);

                //se non trovo l'utente, rispondo con error 5000
                if(user == null) {
                    response.setStatus(Protocol.INVALID_TOKEN);
                    resp.put("msg", "The auth token is not valid");

                    return resp;
                }

                //altrimenti, restituisco 200 e l'oggetto user
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

        //se non ho trovato il token, restituisco il codice di errore
        response.setStatus(Protocol.INVALID_TOKEN);
        resp.put("msg", "The auth token is not valid");

        return resp;

    }

    @SuppressWarnings("unchecked")
    @GetMapping("/logout")
    public JSONObject doLogout(HttpServletRequest request, HttpServletResponse response) {
        JSONObject resp = new JSONObject();
        String token = request.getHeader("Authorization");

        if(token != null && !token.isBlank()) {
            try {
                //cerco l'utente che ha quel token di accesso
                User user = UserDaoJDBC.getInstance().findByToken(token);

                //se non trovo l'utente, rispondo con error 5000
                if(user == null) {
                    response.setStatus(Protocol.INVALID_TOKEN);
                    resp.put("msg", "The auth token is not valid");

                    return resp;
                }

                //altrimenti invalido il token
                UserDaoJDBC.getInstance().saveToken(user.getUsername(), "");
                response.setStatus(Protocol.OK);
                resp.put("msg", "logout successful");

                return resp;

            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(Protocol.SERVER_ERROR);
                resp.put("msg", "Internal server error");

                return resp;
            }
        }

        //se non ho trovato il token, restituisco il codice di errore
        response.setStatus(Protocol.INVALID_TOKEN);
        resp.put("msg", "The auth token is not valid");

        return resp;
    }

    @PostMapping("/registration")
    @SuppressWarnings("unchecked")
    public JSONObject doRegistration(@RequestBody RegistrationFields credentials, HttpServletResponse response) {
        JSONObject resp = new JSONObject();

        try {
            User user = new User();

            user.setEmail(new Email(credentials.email));
            user.setPassword(new Password(credentials.password));
            user.setUsername(new Username(credentials.username));

            UserDaoJDBC.getInstance().save(user);
            response.setStatus(Protocol.OK);
            resp.put("msg", "Account created");

            return resp;
        } catch (SQLException e) {
            if(e.getMessage().contains("violates unique constraint")) {
                response.setStatus(Protocol.USER_ALREADY_EXISTS);
                resp.put("msg", "User already exists");
            }
            else {
                e.printStackTrace();
                response.setStatus(Protocol.SERVER_ERROR);
                resp.put("msg", "Internal server error");
            }

            return resp;
        } catch(IllegalArgumentException | NullPointerException e2) {
            e2.printStackTrace();
            response.setStatus(Protocol.INVALID_CREDENTIALS);
            resp.put("msg", "The provided credentials are not valid");

            return resp;
        }
    }

        @SuppressWarnings("unchecked")
        @PostMapping("/businessLogin")
        public JSONObject doBusinessLogin(@RequestBody Credentials credentials, HttpServletResponse response) {
            Business user = null;
            JSONObject resp = new JSONObject();

            try {
                user = BusinessDaoJDBC.getInstance().checkCredentials(new Email(credentials.username), new Password(credentials.password));

            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(Protocol.SERVER_ERROR);
                resp.put("msg", "Internal server error");

                return resp;
            } catch (IllegalArgumentException | NullPointerException e2) {
                e2.printStackTrace();
                response.setStatus(Protocol.INVALID_CREDENTIALS);
                resp.put("msg", "The provided credentials are not valid");

                return resp;
            }

            if(user == null) {
                response.setStatus(Protocol.WRONG_CREDENTIALS);
                resp.put("msg", "Invalid combination of username and password");

                return resp;
            }

            String token = "";

            try {
                token = BusinessDaoJDBC.getInstance().getToken(credentials.username);

                //se il token è vuoto, ne genero uno nuovo
                if(token== null || token.isBlank()) {
                    String newToken = Utils.generateNewToken();
                    BusinessDaoJDBC.getInstance().saveToken(credentials.username, newToken);

                    token = newToken;
                }


            } catch (SQLException e) {
                e.printStackTrace();
                response.setStatus(Protocol.SERVER_ERROR);
                resp.put("msg", "Internal server error");

                return resp;
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(Protocol.OK);
            resp.put("key", token);

            return resp;
        }

    @PostMapping("/businessRegistration")
    @SuppressWarnings("unchecked")
    public JSONObject doBusinessRegistration(@RequestBody BusinessRegistrationFields credentials, HttpServletResponse response) {
        JSONObject resp = new JSONObject();

        try {
            Business business = new Business();

            business.setEmail(new Email(credentials.email));
            business.setPassword(new Password(credentials.password));
            business.setName(credentials.name);
            business.setAddress(credentials.address);
            business.setCity(credentials.city);
            business.setOwner(credentials.owner);
            business.setPhone(credentials.phone);

            BusinessDaoJDBC.getInstance().save(business);
            response.setStatus(Protocol.OK);
            resp.put("msg", "Account created");

            return resp;
        } catch (SQLException e) {
            if(e.getMessage().contains("violates unique constraint")) {
                response.setStatus(Protocol.USER_ALREADY_EXISTS);
                resp.put("msg", "User already exists");
            }
            else {
                e.printStackTrace();
                response.setStatus(Protocol.SERVER_ERROR);
                resp.put("msg", "Internal server error");
            }

            return resp;
        } catch(IllegalArgumentException | NullPointerException e2) {
            e2.printStackTrace();
            response.setStatus(Protocol.INVALID_CREDENTIALS);
            resp.put("msg", "The provided credentials are not valid");

            return resp;
        }
    }
}