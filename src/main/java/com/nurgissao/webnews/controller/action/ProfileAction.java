package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user.getEmail() + " " + user.getPassword());

        return "profile";
    }
}
