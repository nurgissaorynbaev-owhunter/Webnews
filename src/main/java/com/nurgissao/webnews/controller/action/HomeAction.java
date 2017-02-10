package com.nurgissao.webnews.controller.action;


import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;
import com.nurgissao.webnews.model.entity.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

public class HomeAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();
            ProductOrderDAO productOrderDAO = daoFactory.getProductOrderDAO();
            ShoppingCartDAO shoppingCartDAO = daoFactory.getShoppingCartDAO();

            List<Product> products = productDAO.findAll();
            if (!products.isEmpty()) {
                req.setAttribute("products", products);
            }

            Cookie[] cookies = req.getCookies();
            if (cookies == null) {
                String cookieId = UUID.randomUUID().toString().replace("-", "");
                Cookie cookie = new Cookie("cookieId", cookieId);
                cookie.setMaxAge(90*24*60*60);
                resp.addCookie(cookie);

            } else {
                String cookieId = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cookieId")) {
                        cookieId = cookie.getValue();
                    }
                }
                if (cookieId != null) {
                    ShoppingCart shoppingCart = shoppingCartDAO.find(cookieId);
                    ProductOrder guestsProductOrder = productOrderDAO.findByCustomerId(shoppingCart.getGuestCustomerId());
                    req.getSession().setAttribute("guestsProductOrder", guestsProductOrder);
                }
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
