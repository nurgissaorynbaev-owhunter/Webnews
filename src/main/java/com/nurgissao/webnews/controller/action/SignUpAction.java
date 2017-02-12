package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.UserDAO;
import com.nurgissao.webnews.model.entity.User;
import com.nurgissao.webnews.utils.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SignUpAction implements Action {
    public static final Logger log = Logger.getLogger(SignUpAction.class);
    private static final String USER_NORMAL_STATUS = "normal";
    private static final String USER_ROLE = "user";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            UserDAO userDAO = daoFactory.getUserDAO();
            HttpSession session = req.getSession();

            String firstName = req.getParameter("fname");
            String lastName = req.getParameter("lname");
            String email = req.getParameter("email");
            String password = req.getParameter("pwd");

            Map<String, String> formValue = new HashMap<>();
            formValue.put("firstName", firstName);
            formValue.put("lastName", lastName);
            formValue.put("email", email);
            formValue.put("password", password);

            Validator validator = new Validator();
            Map<String, String> violations = validator.validateSignForm(formValue);
            if (!violations.isEmpty()) {
                session.setAttribute("signUpViolations", violations);
                return "showSignUp";

            } else {
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setRole(USER_ROLE);
                user.setStatus(USER_NORMAL_STATUS);

                User tUser = userDAO.create(user);
                if (tUser != null) {
                    Cookie[] cookies = req.getCookies();
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cookieId")) {
                            cookie.setMaxAge(180 * 24 * 60 * 60);
                            resp.addCookie(cookie);
                        }
                    }
                    session.setMaxInactiveInterval(15 * 24 * 60 * 60);
                    session.setAttribute("user", tUser);
                } else {
                    log.error("Failed to create user.");
                }
                Map<String, String> sViolations = (Map<String, String>) session.getAttribute("signUpViolations");
                if (sViolations != null) {
                    session.removeAttribute("signUpViolations");
                }
            }
        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "showCustomerRegistration";
    }
}

