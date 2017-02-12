package com.nurgissao.webnews.controller;

import com.nurgissao.webnews.controller.action.Action;
import com.nurgissao.webnews.controller.action.ActionException;
import com.nurgissao.webnews.controller.action.ActionFactory;
import com.nurgissao.webnews.model.dao.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pages/*")
public class ControllerServlet extends HttpServlet {
    public static final Logger log = Logger.getLogger(ControllerServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(req);
            String view = action.execute(req, resp);

            if (view.equals(req.getPathInfo().substring(1))) {
                req.getRequestDispatcher("/WEB-INF/view/" + view + ".jsp").forward(req, resp);
            } else {
                resp.sendRedirect(view);
            }

        } catch (ActionException e) {
            log.error("Application Exception", e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.shutdownConnections();
    }
}
