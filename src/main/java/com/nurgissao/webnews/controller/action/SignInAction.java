package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.UserDAO;
import com.nurgissao.webnews.model.entity.User;
import com.nurgissao.webnews.utils.Validator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SignInAction implements Action {
    public static Logger log = Logger.getLogger(SignInAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {

        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            UserDAO userDAO = daoFactory.getUserDAO();

            String email = req.getParameter("email");
            String password = req.getParameter("pwd");
            String rememberMe = req.getParameter("rememberMe");

            Map<String, String> formValue = new HashMap<>();
            formValue.put("email", email);
            formValue.put("password", password);

            Validator validator = new Validator();
            Map<String, String> violations = validator.validateSignForm(formValue);

            if (!violations.isEmpty()) {
                for (Map.Entry<String, String> entry : violations.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }
                return "signIn";
            }

            User user = userDAO.find(email, password);

            if (user != null) {
                HttpSession session = req.getSession();
                if (rememberMe != null) {
                    session.setMaxInactiveInterval(30*24*60*60);
                }
                session.setAttribute("user", user);

            } else {
                log.info("Not such User.");
                //TODO throw appropriate exception
                return "signIn";
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
