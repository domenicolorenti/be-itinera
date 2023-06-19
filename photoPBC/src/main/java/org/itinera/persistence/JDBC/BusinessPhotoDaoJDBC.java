package org.itinera.persistence.JDBC;

import org.itinera.model.BusinessPhoto;
import org.itinera.model.ReviewPhoto;
import org.itinera.persistence.dao.BusinessPhotoDao;
import org.itinera.persistence.dao.ReviewPhotoDao;

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

    }

    @Override
    public byte[] getPhoto(String email) throws SQLException {

        return new byte[0];
    }

}
