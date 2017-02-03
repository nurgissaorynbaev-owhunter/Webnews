package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ShoppingCartDAO shoppingCartDAO = daoFactory.getShoppingCartDAO();
            String cookieId = null;

            Cookie[] cookies = req.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("cookieId")) {
                    cookieId = c.getValue();
                }
            }

            String deleteProductId = req.getParameter("deleteProductId");
            if (deleteProductId != null) {
                shoppingCartDAO.delete(Integer.parseInt(deleteProductId), cookieId);
            }

            List<ShoppingCart> shoppingCarts = shoppingCartDAO.find(cookieId);
            ProductDAO productDAO = daoFactory.getProductDAO();
            List<Product> products = new ArrayList<>();
            Product product;

            if (!shoppingCarts.isEmpty()) {
                for (ShoppingCart sh : shoppingCarts) {
                    product = productDAO.find(sh.getProductId());
                    products.add(product);
                }
                req.setAttribute("products", products);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "shoppingCart";
    }
}
