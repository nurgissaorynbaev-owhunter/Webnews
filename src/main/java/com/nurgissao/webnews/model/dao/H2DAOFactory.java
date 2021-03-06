package com.nurgissao.webnews.model.dao;

public class H2DAOFactory extends DAOFactory {

    @Override
    public UserDAO getUserDAO() {
        return new H2UserDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new H2ProductDAO();
    }

    @Override
    public ShoppingCartDAO getShoppingCartDAO() {
        return new H2ShoppingCartDAO();
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return new H2CustomerDAO();
    }

    @Override
    public ProductOrderDAO getProductOrderDAO() {
        return new H2ProductOrderDAO();
    }
}
