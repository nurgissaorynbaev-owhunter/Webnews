package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ShoppingCartDAO shoppingCartDAO = daoFactory.getShoppingCartDAO();
            String cookieId = null;

            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieId")) {
                    cookieId = cookie.getValue();
                }
            }

            String productId = req.getParameter("productId");
            System.out.println("product id in cart: " + productId);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setProductId(Integer.parseInt(productId));
            shoppingCart.setJssesionid(cookieId);

            shoppingCartDAO.create(shoppingCart);

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
