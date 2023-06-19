package org.itinera.persistence.dao;


import org.itinera.model.BusinessPhoto;

import java.sql.SQLException;
import java.util.List;

public abstract class BusinessPhotoDao implements Dao<BusinessPhoto> {


    @Override
    public abstract List<BusinessPhoto> getAll() throws SQLException;

    @Override
    public abstract void save(BusinessPhoto obj) throws SQLException;

    @Override
    public abstract byte[] getPhoto(String email) throws SQLException;
}
