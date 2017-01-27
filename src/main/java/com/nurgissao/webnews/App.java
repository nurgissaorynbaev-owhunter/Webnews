package com.nurgissao.webnews;


import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.UserDAO;
import com.nurgissao.webnews.model.entity.User;

import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
        UserDAO userDAO = daoFactory.getUserDAO();

    }
}
