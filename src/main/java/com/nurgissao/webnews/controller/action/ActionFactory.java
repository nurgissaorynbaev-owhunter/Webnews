package com.nurgissao.webnews.controller.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("home", new HomeAction());
        actions.put("showSignIn", new ShowSignInAction());
        actions.put("showSignUp", new ShowSignUpAction());
        actions.put("register", new SignUpAction());
        actions.put("signIn", new SignInAction());
        actions.put("profile", new ProfileAction());
    }

    public static Action getAction(HttpServletRequest req) {
        String key = req.getPathInfo().substring(1);
        System.out.println(key);
        return actions.get(key);
    }
}
