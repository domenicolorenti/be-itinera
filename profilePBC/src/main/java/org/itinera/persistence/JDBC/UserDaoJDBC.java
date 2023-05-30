package org.itinera.persistence.JDBC;

import org.itinera.Utils;
import org.itinera.model.User;
import org.itinera.persistence.DBConnection;
import org.itinera.persistence.dao.UserDao;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;
import org.itinera.persistence.domain.Username;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoJDBC extends UserDao  {



    private static UserDaoJDBC instance = null;

    private UserDaoJDBC() {}

    public static UserDaoJDBC getInstance() {
        if(instance == null)
            instance = new UserDaoJDBC();
        return instance;
    }

    @Override
    public synchronized List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public synchronized void save(User obj) throws SQLException {
        String saveUserQuery = "insert into users values(?, ?, ?, '')";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveUserQuery);
        stm.setString(1, obj.getUsername());
        stm.setString(2, obj.getEmail());
        stm.setString(3, Utils.hashPassword(obj.getPassword()));


        stm.execute();

        stm.close();
    }

    @Override
    public synchronized User findByToken(String token) throws SQLException, IllegalArgumentException, NullPointerException {
        String findByTokenQuery = "select username, email from users where token=? and token !=''";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(findByTokenQuery);
        stm.setString(1, token);

        ResultSet rs = stm.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setUsername(new Username(rs.getString("username")));
            user.setEmail(new Email(rs.getString("email")));
        }

        rs.close();
        stm.close();

        return user;
    }

    @Override
    public synchronized User findByEmail(Email email) throws SQLException, IllegalArgumentException, NullPointerException {
        return null;
    }

    @Override
    public synchronized String getToken(String username) throws SQLException {
        return null;
    }

    @Override
    public synchronized User checkCredentials(Username username, Password password) throws SQLException {
        String checkCredentialsQuery = "select * from users where username=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(checkCredentialsQuery);
        stm.setString(1, username.toString());

        ResultSet rs = stm.executeQuery();
        User user = null;
        if (rs.next()) {
            String dbPassword = rs.getString("password");

            if (Utils.checkPassword(dbPassword, password.toString()))
                user = User.parseFromDB(rs);
        }

        rs.close();
        stm.close();

        return user;
    }

    @Override
    public synchronized void saveToken(String user, String token) throws SQLException {
        String saveTokenQuery = "update users set token=? where username=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveTokenQuery);
        stm.setString(1, token);
        stm.setString(2, user);

        stm.execute();

        stm.close();
    }

    @Override
    public synchronized void deleteToken(String token) throws SQLException {
        String deleteTokenQuery = "delete from tokens where token=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(deleteTokenQuery);
        stm.setString(1, token);

        stm.execute();
        stm.close();
    }
}
