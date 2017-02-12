package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.UserDAO;
import com.nurgissao.webnews.model.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersAction implements Action {
    public static Logger log = Logger.getLogger(UsersAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            UserDAO userDAO = daoFactory.getUserDAO();

            String bannedUserId = req.getParameter("bannedUserId");
            String unbannedUserId = req.getParameter("unbannedUserId");
            String deletedUserId = req.getParameter("deletedUserId");

            if (deletedUserId != null) {
                User user = userDAO.findById(Integer.parseInt(deletedUserId));

                int affectedRowCount = userDAO.delete(user);
                if (affectedRowCount == 0) {
                    log.info("User not deleted.");
                }

            } else if (bannedUserId != null) {
                User user = userDAO.findById(Integer.parseInt(bannedUserId));
                if (user != null) {
                    user.setStatus("ban");

                    int affectedRowCount = userDAO.update(user);
                    if (affectedRowCount == 0) {
                        log.info("User not updated.");
                    }

                } else {
                    log.info("User is null.");
                }
            } else if (unbannedUserId != null) {
                User user = userDAO.findById(Integer.parseInt(unbannedUserId));
                if (user != null) {
                    user.setStatus("unban");

                    int affectedRowCount = userDAO.update(user);
                    if (affectedRowCount == 0) {
                        log.info("User not updated.");
                    }
                } else {
                    log.info("User is null.");
                }
            }
            List<User> users = userDAO.findAll();
            if (!users.isEmpty()) {
                req.setAttribute("users", users);
            }
        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "users";
    }
}
