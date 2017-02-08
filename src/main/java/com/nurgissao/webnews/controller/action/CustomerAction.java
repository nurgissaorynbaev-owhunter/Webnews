package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Customer;
import com.nurgissao.webnews.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CustomerAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            CustomerDAO customerDAO = daoFactory.getCustomerDAO();
            Validator validator = new Validator();

            String fullName = req.getParameter("fullName");
            String country = req.getParameter("country");
            String city = req.getParameter("city");
            String homeAddress = req.getParameter("homeAddress");
            String phoneNumber = req.getParameter("phoneNumber");
            String email = req.getParameter("email");

            Map<String, String> formValue = new HashMap<>();
            formValue.put("fullName", fullName);
            formValue.put("country", country);
            formValue.put("city", city);
            formValue.put("homeAddress", homeAddress);
            formValue.put("phoneNumber", phoneNumber);
            formValue.put("email", email);

            Map<String, String> violations = validator.validateCustomerRegistrationForm(formValue);
            if (!violations.isEmpty()) {
                //TODO throw appropriate Exception

            } else {
                Customer customer = new Customer();
                customer.setFullName(fullName);
                customer.setCountry(country);
                customer.setCity(city);
                customer.setHomeAddress(homeAddress);
                customer.setPhoneNumber(phoneNumber);
                customer.setEmail(email);

                Customer tCustomer = customerDAO.create(customer);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "showProductOrder";
    }
}
