package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Customer;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.ProductOrder;
import com.nurgissao.webnews.model.entity.User;
import com.nurgissao.webnews.utils.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInAction implements Action {
    public static Logger log = Logger.getLogger(SignInAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            UserDAO userDAO = daoFactory.getUserDAO();
            CustomerDAO customerDAO = daoFactory.getCustomerDAO();
            ProductOrderDAO productOrderDAO = daoFactory.getProductOrderDAO();
            ProductDAO productDAO = daoFactory.getProductDAO();

            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            String rememberMe = req.getParameter("rememberMe");

            Map<String, String> formValue = new HashMap<>();
            formValue.put("email", email);
            formValue.put("password", password);

            Validator validator = new Validator();
            Map<String, String> violations = validator.validateSignForm(formValue);

            if (!violations.isEmpty()) {
                req.getSession().setAttribute("signInViolations", violations);
                return "showSignIn";
            }

            User user = userDAO.find(email, password);
            if (user != null) {
                HttpSession session = req.getSession();
                if (rememberMe != null) {
                    session.setMaxInactiveInterval(7 * 24 * 60 * 60);
                }

                List<ProductOrder> productOrders = productOrderDAO.findAllByCustomerId(user.getCustomerId());
                if (!productOrders.isEmpty()) {
                    Map<Integer, Product> productsMap = new HashMap<>();
                    Map<Integer, Integer> quantityMap = new HashMap<>();
                    for (ProductOrder productOrder : productOrders) {
                        Product product = productDAO.findById(productOrder.getProductId());
                        productsMap.put(productOrder.getId(), product);
                        quantityMap.put(productOrder.getId(), productOrder.getProductQuantity());
                    }
                    session.setAttribute("productsMap", productsMap);
                    session.setAttribute("quantityMap", quantityMap);
                    session.setAttribute("productOrders", productOrders);
                }

                session.setAttribute("user", user);

                Customer customer = customerDAO.findById(user.getCustomerId());
                if (customer != null) {
                    session.setAttribute("customer", customer);
                } else {
                    //TODO throw appropriate exception
                }
                req.getSession().removeAttribute("signInViolations");

            } else {
                violations.put("Error","Please check email or password.");
                req.getSession().setAttribute("signInViolations", violations);
                return "showSignIn";
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
