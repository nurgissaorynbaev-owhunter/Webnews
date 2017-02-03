package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {

    List<ShoppingCart> find(String cookieId) throws DAOException;

    ShoppingCart find(int productId, String cookieId) throws DAOException;

    ShoppingCart create(ShoppingCart shoppingCart) throws DAOException;

    int update(ShoppingCart shoppingCart) throws DAOException;

    int delete(ShoppingCart shoppingCart) throws DAOException;

    int delete(int productId, String cookieId) throws DAOException;

}
