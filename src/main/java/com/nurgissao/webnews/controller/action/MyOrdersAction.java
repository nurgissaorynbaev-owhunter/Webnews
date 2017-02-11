package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;
import com.nurgissao.webnews.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

            List<ProductOrder> productsOrder = null;
            User user = (User) session.getAttribute("user");
            if (user != null) {
                productsOrder = productOrderDAO.findAllByCustomerId(user.getCustomerId());
            }

            Map<Integer, Product> productOrderMap = new HashMap<>();
            Map<Integer, Integer> productQuantityOrderMap = new HashMap<>();
            for (ProductOrder productOrder : productsOrder) {
                Product product = productDAO.find(productOrder.getProductId());
                productOrderMap.put(productOrder.getId(), product);
                productQuantityOrderMap.put(productOrder.getId(), productOrder.getProductQuantity());
            }
            session.setAttribute("productOrderMap", productOrderMap);
            session.setAttribute("productQuantityOrderMap", productQuantityOrderMap);
            session.setAttribute("productsOrder", productsOrder);

        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "myOrders";
    }
}
