package com.nurgissao.webnews.model.dao;


import com.nurgissao.webnews.model.entity.User;

import java.util.List;

public interface UserDAO {

    User find(int id) throws DAOException;

    User find(String email, String password) throws DAOException;

    List<User> findAll() throws DAOException;

    User create(User user) throws DAOException;

    int update(User user) throws DAOException;

    int delete(User user) throws DAOException;

    int changePassword(User user) throws DAOException;
}
