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
            String cookieId = null;

            Cookie[] cookies = req.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("cookieId")) {
                    cookieId = c.getValue();
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
                int affectedRowCount = shoppingCartDAO.delete(Integer.parseInt(deleteProductId), cookieId);
                if (affectedRowCount == 0) {
                    //TODO throw appropriate Exception
                }
            }

            ProductDAO productDAO = daoFactory.getProductDAO();
            List<Product> products = new ArrayList<>();
            Product product;
            Map<Integer, Integer> productQuantity = new HashMap<>();

            List<ShoppingCart> shoppingCarts = shoppingCartDAO.find(cookieId);
            if (!shoppingCarts.isEmpty()) {
                for (ShoppingCart sh : shoppingCarts) {
                    productQuantity.put(sh.getProductId(), sh.getQuantity());
                    product = productDAO.find(sh.getProductId());
                    products.add(product);
                }
                HttpSession session = req.getSession();
                session.setAttribute("products", products);
                session.setAttribute("productQuantity", productQuantity);
//                req.setAttribute("products", products);
//                req.setAttribute("productQuantity", productQuantity);

                int totalCost = 0;
                for (Product p : products) {
                    int quantity = productQuantity.get(p.getId());
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
