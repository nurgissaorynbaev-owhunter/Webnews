package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddShoppingCartAction implements Action {

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

            int productId = Integer.parseInt(req.getParameter("productId"));

            ShoppingCart shoppingCart = new ShoppingCart();
            ShoppingCart takenShoppingCart = shoppingCartDAO.find(productId, cookieId);

            int quantity = 0;
            if (takenShoppingCart != null) {
                quantity = takenShoppingCart.getQuantity();
                quantity ++;
            } else {

            }
            shoppingCart.setProductId(productId);
            shoppingCart.setCookieId(cookieId);
            shoppingCart.setQuantity(quantity);

            shoppingCartDAO.create(shoppingCart);

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
