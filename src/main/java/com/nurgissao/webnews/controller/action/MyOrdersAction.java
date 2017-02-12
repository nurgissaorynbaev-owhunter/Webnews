package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;
import com.nurgissao.webnews.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrdersAction implements Action {
    public static Logger log = Logger.getLogger(MyOrdersAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductOrderDAO productOrderDAO = daoFactory.getProductOrderDAO();
            ProductDAO productDAO = daoFactory.getProductDAO();

            List<ProductOrder> productsOrder;
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                productsOrder = productOrderDAO.findAllByCustomerId(user.getCustomerId());

                Map<Integer, Product> productOrderMap = new HashMap<>();
                Map<Integer, Integer> productQuantityOrderMap = new HashMap<>();
                for (ProductOrder productOrder : productsOrder) {
                    Product product = productDAO.findById(productOrder.getProductId());
                    productOrderMap.put(productOrder.getId(), product);
                    productQuantityOrderMap.put(productOrder.getId(), productOrder.getProductQuantity());
                }

                req.setAttribute("productOrderMap", productOrderMap);
                req.setAttribute("productQuantityOrderMap", productQuantityOrderMap);
                req.setAttribute("productsOrder", productsOrder);

            } else {
                log.info("user is null.");
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "myOrders";
    }
}
