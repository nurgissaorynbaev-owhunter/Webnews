package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer find(int id) throws DAOException;

    List<Customer> findAll() throws DAOException;

    Customer create(Customer customer) throws DAOException;

    int update(Customer customer) throws DAOException;

    int delete(Customer customer) throws DAOException;
}
