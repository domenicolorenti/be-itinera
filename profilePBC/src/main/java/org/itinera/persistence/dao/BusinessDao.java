package org.itinera.persistence.dao;

import org.itinera.model.Business;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;
import org.itinera.persistence.domain.Username;

import java.sql.SQLException;
import java.util.List;

public abstract class BusinessDao implements Dao<Business>{

    public abstract List<Business> getAll() throws SQLException;
    public abstract void save(Business obj) throws SQLException;

    public abstract Business findByToken(String token) throws SQLException, IllegalArgumentException, NullPointerException;
    public abstract Business findByEmail(Email email) throws SQLException, IllegalArgumentException, NullPointerException;
    public abstract String getToken(String email) throws SQLException;
    public abstract Business checkCredentials(Email email, Password password) throws SQLException;
    public abstract void saveToken(String email, String token) throws SQLException;
    public abstract void deleteToken(String token) throws SQLException;
}