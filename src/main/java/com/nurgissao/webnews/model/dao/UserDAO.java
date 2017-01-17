package com.nurgissao.webnews.model.dao;


import com.nurgissao.webnews.model.entity.User;

import java.util.List;

public interface UserDAO {

    public User find(int id) throws DAOException;

    public User find(String email, String password) throws DAOException;

    public List<User> findAll() throws DAOException;

    public User create(User user) throws DAOException;

    public int update(User user) throws DAOException;

    public int delete(User user) throws DAOException;

    public User changePassword(User user) throws DAOException;
}
