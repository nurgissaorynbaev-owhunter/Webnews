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
        actions.put("signOut", new SignOutAction());

        actions.put("showProfile", new ShowProfileAction());
        actions.put("profile", new ProfileAction());

        actions.put("addShoppingCart", new AddShoppingCartAction());
        actions.put("shoppingCart", new ShoppingCartAction());

        actions.put("showAddProduct", new ShowAddProductAction());
        actions.put("productDetails", new ProductDetailsAction());
        actions.put("addProduct", new AddProductAction());
        actions.put("deleteProduct", new DeleteProductAction());
        actions.put("editProduct", new EditProductAction());
    }

    public static Action getAction(HttpServletRequest req) {
        String key = req.getPathInfo().substring(1);
        System.out.println(key);
        return actions.get(key);
    }
}
