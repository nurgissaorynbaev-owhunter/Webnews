package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ShoppingCartDAO shoppingCartDAO = daoFactory.getShoppingCartDAO();
            ProductDAO productDAO = daoFactory.getProductDAO();
            String cookieId = null;

            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieId")) {
                    cookieId = cookie.getValue();
                }
            }

            String pQuantity = req.getParameter("pQuantity");
            String productId = req.getParameter("productId");
            if (pQuantity != null && productId != null) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setQuantity(Integer.parseInt(pQuantity));
                shoppingCart.setProductId(Integer.parseInt(productId));

                int affectedRowCount = shoppingCartDAO.update(shoppingCart);
                if (affectedRowCount == 0) {
                    //TODO throw appropriate Exception
                }
            }

            String deleteProductId = req.getParameter("deleteProductId");
            if (deleteProductId != null) {
                ShoppingCart shoppingCart = shoppingCartDAO.find(Integer.parseInt(deleteProductId), cookieId);
                if (shoppingCart != null) {
                    System.out.println("shopping cart id:" + shoppingCart.getId());
                    shoppingCartDAO.delete(shoppingCart);

                } else {
                    //TODO throw appropriate Exception
                }
            }

            List<Product> products = new ArrayList<>();
            Map<Integer, Integer> productQuantityMap = new HashMap<>();
            Product product;

            List<ShoppingCart> shoppingCartItems = shoppingCartDAO.findAllByCookieId(cookieId);
            if (!shoppingCartItems.isEmpty()) {
                for (ShoppingCart sh : shoppingCartItems) {
                    productQuantityMap.put(sh.getProductId(), sh.getQuantity());
                    product = productDAO.find(sh.getProductId());
                    products.add(product);
                }
                HttpSession session = req.getSession();
                session.setAttribute("products", products);
                session.setAttribute("productQuantityMap", productQuantityMap);

                int totalCost = 0;
                for (Product p : products) {
                    int quantity = productQuantityMap.get(p.getId());
                    int cost = p.getPrice() * quantity;
                    totalCost = totalCost + cost;
                }
                session.setAttribute("totalCost", totalCost);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "shoppingCart";
    }
}
