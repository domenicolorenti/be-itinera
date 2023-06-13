package org.itinera.persistence.JDBC;

import org.itinera.Utils;
import org.itinera.model.Business;
import org.itinera.persistence.DBConnection;
import org.itinera.persistence.dao.BusinessDao;
import org.itinera.persistence.domain.Email;
import org.itinera.persistence.domain.Password;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessDaoJDBC extends BusinessDao {

    private static BusinessDaoJDBC instance = null;

    private BusinessDaoJDBC() {}

    public static BusinessDaoJDBC getInstance() {
        if(instance == null)
            instance = new BusinessDaoJDBC();
        return instance;
    }

    @Override
    public List<Business> getAll() throws SQLException {
        String getAllQuery = "SELECT * FROM businesses";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getAllQuery);
        ResultSet rs = stm.executeQuery();

        ArrayList<Business> businesses = new ArrayList<>();
        while(rs.next()) {
            Business business = Business.parseFromDB(rs);
            businesses.add(business);
        }

        stm.close();
        rs.close();
        return businesses;
    }

    @Override
    public void save(Business obj) throws SQLException {
        String saveUserQuery = "insert into businesses values(?, ?, ?, ?, ?, ?, ?, '', ?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveUserQuery);

        stm.setString(1, obj.getEmail());
        stm.setString(2, obj.getName());
        stm.setString(3, Utils.hashPassword(obj.getPassword()));
        stm.setString(4, obj.getAddress());
        stm.setString(5, obj.getCity());
        stm.setString(6, obj.getOwner());
        stm.setString(7, obj.getPhone());
        stm.setString(8, "Account don't have a description");

        stm.execute();

        stm.close();
    }

    @Override
    public Business findByToken(String token) throws SQLException, IllegalArgumentException, NullPointerException {
        String findByTokenQuery = "select * from businesses where token=? and token !=''";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(findByTokenQuery);
        stm.setString(1, token);

        ResultSet rs = stm.executeQuery();
        Business user = null;
        if (rs.next()) {
            user = new Business();
            user.setName(rs.getString("name"));
            user.setEmail(new Email(rs.getString("email")));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setOwner(rs.getString("owner"));
            user.setPhone(rs.getString("phone"));
            user.setDescription(rs.getString("description"));
        }

        rs.close();
        stm.close();

        return user;
    }

    @Override
    public Business findByEmail(Email email) throws SQLException, IllegalArgumentException, NullPointerException {
        String findByEmailQuery = "select * from businesses where email=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(findByEmailQuery);
        stm.setString(1, email.toString());

        ResultSet rs = stm.executeQuery();
        Business user = null;
        if (rs.next()) {
            user = new Business();
            user.setName(rs.getString("name"));
            user.setEmail(new Email(rs.getString("email")));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));
            user.setOwner(rs.getString("owner"));
            user.setPhone(rs.getString("phone"));
            user.setDescription(rs.getString("description"));
        }

        rs.close();
        stm.close();

        return user;
    }

    @Override
    public String getToken(String email) throws SQLException {
        return null;
    }

    @Override
    public Business checkCredentials(Email email, Password password) throws SQLException {
        String checkCredentialsQuery = "select * from businesses where email=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(checkCredentialsQuery);
        stm.setString(1, email.toString());

        ResultSet rs = stm.executeQuery();
        Business user = null;
        if (rs.next()) {
            String dbPassword = rs.getString("password");

            if (Utils.checkPassword(dbPassword, password.toString()))
                user = Business.parseFromDB(rs);
        }

        rs.close();
        stm.close();

        return user;
    }

    @Override
    public void saveToken(String email, String token) throws SQLException {
        String saveTokenQuery = "update businesses set token=? where email=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveTokenQuery);
        stm.setString(1, token);
        stm.setString(2, email);

        stm.execute();

        stm.close();
    }

    @Override
    public void deleteToken(String token) throws SQLException {

    }

    public List<String> getCities() throws SQLException{
        String getCitiesQuery = "select city from businesses";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getCitiesQuery);

        ResultSet rs = stm.executeQuery();
        List<String> cities = new ArrayList<>();
        while(rs.next()) {
            cities.add(rs.getString("city"));
        }

        rs.close();
        stm.close();

        return cities;
    }
}
