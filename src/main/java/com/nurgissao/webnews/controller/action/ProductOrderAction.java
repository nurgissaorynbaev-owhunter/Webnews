package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductOrderDAO;
import com.nurgissao.webnews.model.entity.Customer;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class ProductOrderAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductOrderDAO productOrderDAO = daoFactory.getProductOrderDAO();

            HttpSession session = req.getSession();
            List<Product> products = (List<Product>) session.getAttribute("products");
            Map<Integer, Integer> productQuantityMap = (Map<Integer, Integer>) session.getAttribute("productQuantityMap");
            Customer customer = (Customer) session.getAttribute("customer");

            for (Product product : products) {
                ProductOrder productOrder = new ProductOrder();

                productOrder.setProductId(product.getId());
                productOrder.setProductQuantity(productQuantityMap.get(product.getId()));
                productOrder.setCustomerId(customer.getId());

                productOrderDAO.create(productOrder);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "myOrders";
    }
}
