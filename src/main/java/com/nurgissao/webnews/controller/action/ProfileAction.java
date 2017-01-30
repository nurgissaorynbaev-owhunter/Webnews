package com.nurgissao.webnews.controller.action;


import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.UserDAO;
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

            String firstName = req.getParameter("fname");
            String lastName = req.getParameter("lname");
            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            String confirmPassword = req.getParameter("confirmPwd");

            Map<String, String> formValue = new HashMap<>();
            formValue.put("firstName", firstName);
            formValue.put("lastName", lastName);
            formValue.put("email", email);
            formValue.put("password", password);
            formValue.put("confirmPassword", confirmPassword);

            Validator validator = new Validator();
            Map<String, String> violations = validator.validateProfileForm(formValue);

            if (!violations.isEmpty()) {
                for (Map.Entry<String, String> entry : violations.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
                return "profile";
            }

            HttpSession session = req.getSession();
            User userValue = (User) session.getAttribute("user");

            User user = new User();
            user.setId(userValue.getId());
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);

            int result = userDAO.update(user);
            if (result != 0) {
                session.setAttribute("user", user);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
