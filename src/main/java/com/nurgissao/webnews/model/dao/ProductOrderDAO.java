package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.ProductOrder;

import java.util.List;

public interface ProductOrderDAO {

    ProductOrder findById(int id) throws DAOException;

    List<ProductOrder> findAllByCustomerId(int customerId) throws DAOException;

    List<ProductOrder> findAll() throws DAOException;

    ProductOrder create(ProductOrder productOrder) throws DAOException;

    int update(ProductOrder productOrder) throws DAOException;

    int delete(ProductOrder productOrder) throws DAOException;
}
