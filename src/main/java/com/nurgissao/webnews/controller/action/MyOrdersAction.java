package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;
import com.nurgissao.webnews.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrdersAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductOrderDAO productOrderDAO = daoFactory.getProductOrderDAO();
            ProductDAO productDAO = daoFactory.getProductDAO();

            HttpSession session = req.getSession();

            User user = (User) session.getAttribute("user");
            if (user != null) {
                List<ProductOrder> productOrders = productOrderDAO.findAll(user.getCustomerId());

                Map<Integer, Product> productsMap = new HashMap<>();
                Map<Integer, Integer> quantityMap = new HashMap<>();
                for (ProductOrder productOrder : productOrders) {
                    Product product = productDAO.find(productOrder.getProductId());
                    productsMap.put(productOrder.getId(), product);
                    quantityMap.put(productOrder.getId(), productOrder.getProductQuantity());
                }
                session.setAttribute("productsMap", productsMap);
                session.setAttribute("quantityMap", quantityMap);
                session.setAttribute("productOrders", productOrders);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "myOrders";
    }
}
