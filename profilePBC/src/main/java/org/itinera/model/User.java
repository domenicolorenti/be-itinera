package org.itinera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;
import org.itinera.persistence.domain.Username;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private Username username;
    private Email email;
    @JsonIgnore
    private Password password;

    public User() {}

    public User(Username username, Password password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username.toString();
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public String getPassword() {
        return password.toString();
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public String getEmail() {
        return email.toString();
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public static User parseFromDB(ResultSet rs) throws SQLException, IllegalArgumentException, NullPointerException {
        User user = new User();
        user.setEmail(new Email(rs.getString("email")));
        user.setUsername(new Username(rs.getString("username")));

        return user;
    }
}
