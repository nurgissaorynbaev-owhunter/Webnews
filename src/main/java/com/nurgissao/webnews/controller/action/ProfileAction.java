package com.nurgissao.webnews.controller.action;


import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Customer;
import com.nurgissao.webnews.model.entity.User;
import com.nurgissao.webnews.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class ProfileAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            UserDAO userDAO = daoFactory.getUserDAO();
            CustomerDAO customerDAO = daoFactory.getCustomerDAO();

            String firstName = req.getParameter("fname");
            String lastName = req.getParameter("lname");
            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            String confirmPassword = req.getParameter("confirmPwd");

            String fullName = firstName + " " + lastName;
            String country = req.getParameter("country");
            String city = req.getParameter("city");
            String homeAddress = req.getParameter("homeAddress");
            String phoneNumber = req.getParameter("phoneNumber");

            Map<String, String> userFormValue = new HashMap<>();
            Map<String, String> customerFormValue = new HashMap<>();

            userFormValue.put("firstName", firstName);
            userFormValue.put("lastName", lastName);
            userFormValue.put("email", email);
            userFormValue.put("password", password);
            userFormValue.put("confirmPassword", confirmPassword);

            customerFormValue.put("country", country);
            customerFormValue.put("city", city);
            customerFormValue.put("homeAddress", homeAddress);
            customerFormValue.put("phoneNumber", phoneNumber);

            Validator validator = new Validator();
            Map<String, String> userViolations = validator.validateProfileForm(userFormValue);
            Map<String, String> customerViolations = validator.validateCustomerRegistrationForm(customerFormValue);


            if (!(userViolations.isEmpty() && customerViolations.isEmpty())) {
                for (Map.Entry<String, String> entry : userViolations.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
                return "profile";

            } else {
                HttpSession session = req.getSession();
                User sessionUser = (User) session.getAttribute("user");

                User user = new User();
                user.setId(sessionUser.getId());
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);

                Customer customer = new Customer();
                customer.setId(sessionUser.getCustomerId());
                customer.setFullName(fullName);
                customer.setCountry(country);
                customer.setCity(city);
                customer.setHomeAddress(homeAddress);
                customer.setPhoneNumber(phoneNumber);

                int affectedUserRowCount = userDAO.update(user);
                int affectedCustomerRowCount = customerDAO.update(customer);
                if (affectedUserRowCount != 0 && affectedCustomerRowCount != 0) {
                    session.setAttribute("user", user);
                    session.setAttribute("customer", customer);

                } else {
                    //TODO throw appropriate Exception
                }
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
