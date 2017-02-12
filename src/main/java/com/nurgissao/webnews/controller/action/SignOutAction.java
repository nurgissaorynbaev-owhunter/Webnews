package com.nurgissao.webnews.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.removeAttribute("customer");

        session.removeAttribute("productOrderMap");
        session.removeAttribute("productQuantityOrderMap");
        session.removeAttribute("productsOrder");

        return "home";
    }
}
