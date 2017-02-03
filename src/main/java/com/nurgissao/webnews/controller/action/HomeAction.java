package com.nurgissao.webnews.controller.action;


import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

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

            Cookie[] cookies = req.getCookies();
            if (cookies == null) {
                String cookieId = UUID.randomUUID().toString().replace("-", "");
                Cookie cookie = new Cookie("cookieId", cookieId);
                resp.addCookie(cookie);
            } else {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cookieId")) {
                        req.setAttribute("cookieId", cookie.getValue());
                    }
                }
            }

//            String deleteProductId = req.getParameter("deleteProductId");
//            ShoppingCartDAO shoppingCartDAO = daoFactory.getShoppingCartDAO();
//            if (deleteProductId != null) {
//                System.out.println("delete id: " + deleteProductId);
//                String jsessionid = (String) req.getAttribute("jsessionid");
//                shoppingCartDAO.delete(Integer.parseInt(deleteProductId), jsessionid);
//            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
