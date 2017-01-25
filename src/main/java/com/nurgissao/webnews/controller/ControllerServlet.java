package com.nurgissao.webnews.controller;

import com.nurgissao.webnews.controller.action.Action;
import com.nurgissao.webnews.controller.action.ActionException;
import com.nurgissao.webnews.controller.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/pages/*")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Action action = ActionFactory.getAction(req);
            String view = action.execute(req, resp);

            req.getRequestDispatcher("/WEB-INF/view/" + view + ".jsp").forward(req, resp);

        } catch (ActionException e) {
            e.printStackTrace();
        }
    }
}
