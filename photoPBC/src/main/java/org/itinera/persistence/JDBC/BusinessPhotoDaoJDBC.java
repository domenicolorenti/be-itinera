package org.itinera.persistence.JDBC;

import org.itinera.model.BusinessPhoto;
import org.itinera.persistence.DBConnection;
import org.itinera.persistence.dao.BusinessPhotoDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BusinessPhotoDaoJDBC extends BusinessPhotoDao {

    private static BusinessPhotoDaoJDBC instance = null;

    private BusinessPhotoDaoJDBC() {}

    public static BusinessPhotoDaoJDBC getInstance() {
        if(instance == null)
            instance = new BusinessPhotoDaoJDBC();
        return instance;
    }

    @Override
    public List<BusinessPhoto> getAll() throws SQLException {

        return null;
    }

    @Override
    public void save(BusinessPhoto obj) throws SQLException {
        String saveQuery = "insert into businessPhoto values(?, ?)";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(saveQuery);

        stm.setString(1, obj.getEmail());
        stm.setBytes(2, obj.getPhoto());

        stm.execute();

        stm.close();
    }

    @Override
    public BusinessPhoto getPhoto(String email) throws SQLException {

        String getPhotoQuery = "SELECT * FROM businessPhoto where business=?";
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(getPhotoQuery);
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();

        BusinessPhoto businessPhoto = null;
        if(rs.next())
            businessPhoto = BusinessPhoto.parseFromDB(rs);

        stm.close();
        rs.close();
        return businessPhoto;
    }

}
