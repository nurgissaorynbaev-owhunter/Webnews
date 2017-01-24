package com.nurgissao.webnews;


import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.UserDAO;
import com.nurgissao.webnews.model.entity.User;


public class App {

    public static void main(String[] args) throws DAOException {

        DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
        UserDAO userDAO = daoFactory.getUserDAO();

        User user = new User();
        user.setId(19);
//        user.setFirstName("foo");
//        user.setLastName("bar");
//        user.setEmail("bar@mail.com");
//        user.setPassword("4297979387");

        System.out.println(userDAO.delete(user));
    }
}
