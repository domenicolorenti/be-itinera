package org.itinera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Business implements GeneralUser {

    private String name;
    private Email email;
    @JsonIgnore
    private Password password;
    private String address;
    private String city;
    private String owner;
    private String phone;
    private String description;

    //from other PBC
    private float vote;

    public Business() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Business parseFromDB(ResultSet rs) throws SQLException, IllegalArgumentException, NullPointerException {
        Business business = new Business();
        business.setEmail(new Email(rs.getString("email")));
        business.setName(rs.getString("name"));
        business.setAddress(rs.getString("address"));
        business.setCity(rs.getString("city"));
        business.setOwner(rs.getString("owner"));
        business.setPhone(rs.getString("phone"));
        business.setDescription(rs.getString("description"));

        return business;
    }



    //from other PBC

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }
}