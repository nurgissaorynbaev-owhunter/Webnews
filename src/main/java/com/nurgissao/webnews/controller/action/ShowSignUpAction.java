package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.controller.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowSignUpAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "signUp";
    }
}
