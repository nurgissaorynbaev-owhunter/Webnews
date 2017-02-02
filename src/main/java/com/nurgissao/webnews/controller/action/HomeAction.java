package com.nurgissao.webnews.controller.action;


import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();

            List<Product> products = productDAO.findAll();
            if (!products.isEmpty()) {
                req.setAttribute("products", products);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
