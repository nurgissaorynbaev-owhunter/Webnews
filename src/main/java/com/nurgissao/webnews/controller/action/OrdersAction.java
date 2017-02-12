package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Customer;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersAction implements Action {
    public static Logger log = Logger.getLogger(OrdersAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductOrderDAO productOrderDAO = daoFactory.getProductOrderDAO();
            ProductDAO productDAO = daoFactory.getProductDAO();
            CustomerDAO customerDAO = daoFactory.getCustomerDAO();

            Map<Integer, Product> productMap = new HashMap<>();
            Map<Integer, Customer> customerMap = new HashMap<>();
            List<ProductOrder> productOrders = productOrderDAO.findAll();
            if (!productOrders.isEmpty()) {
                for (ProductOrder productOrder : productOrders) {

                    Product product = productDAO.findById(productOrder.getProductId());
                    Customer customer = customerDAO.findById(productOrder.getCustomerId());

                    productMap.put(productOrder.getId(), product);
                    customerMap.put(productOrder.getId(), customer);
                }

                req.setAttribute("productMap", productMap);
                req.setAttribute("customerMap", customerMap);
                req.setAttribute("productOrders", productOrders);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "orders";
    }
}
