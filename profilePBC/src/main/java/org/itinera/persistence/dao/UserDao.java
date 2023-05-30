package org.itinera.persistence.dao;

import org.itinera.model.User;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;
import org.itinera.persistence.domain.Username;

import java.sql.SQLException;
import java.util.List;

public abstract class UserDao implements Dao<User>{

    public abstract List<User> getAll() throws SQLException;
    public abstract void save(User obj) throws SQLException;

    public abstract User findByToken(String token) throws SQLException, IllegalArgumentException, NullPointerException;
    public abstract User findByEmail(Email email) throws SQLException, IllegalArgumentException, NullPointerException;
    public abstract String getToken(String username) throws SQLException;
    public abstract User checkCredentials(Username username, Password password) throws SQLException;
    public abstract void saveToken(String user, String token) throws SQLException;
    public abstract void deleteToken(String token) throws SQLException;
}
