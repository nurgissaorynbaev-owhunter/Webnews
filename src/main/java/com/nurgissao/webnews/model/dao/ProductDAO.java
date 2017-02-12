package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.Product;

import java.util.List;

public interface ProductDAO {

    Product findById(int id) throws DAOException;

    List<Product> findAll() throws DAOException;

    Product create(Product product) throws DAOException;

    int update(Product product) throws DAOException;

    int delete(Product product) throws DAOException;
}
