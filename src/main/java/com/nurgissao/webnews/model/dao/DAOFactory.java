package com.nurgissao.webnews.model.dao;

public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(DataSourceType type) throws DAOException {
        switch (type) {
            case H2:
                return new H2DAOFactory();
            default:
                throw new DAOException("Not such type of data source.");
        }
    }

    public abstract UserDAO getUserDAO();
    public abstract ProductDAO getProductDAO();
}
