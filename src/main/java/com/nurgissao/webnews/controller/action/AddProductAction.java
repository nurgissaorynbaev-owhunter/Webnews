package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductDAO;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.utils.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddProductAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();

            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String price = req.getParameter("price");
            String description = req.getParameter("description");
            String details = req.getParameter("details");
            String aboutAuthor = req.getParameter("aboutAuthor");

            Map<String, String> formValue = new HashMap<>();
            formValue.put("title", title);
            formValue.put("author", author);
            formValue.put("price", price);
            formValue.put("description", description);
            formValue.put("details", details);
            formValue.put("aboutAuthor", aboutAuthor);

            Validator validator = new Validator();
            Map<String, String> violations = validator.validateAddProductForm(formValue);

            if (!violations.isEmpty()) {
                for (Map.Entry<String, String> entry : violations.entrySet()){
                    System.out.println(entry.getKey() + " " + entry.getValue());

                    //TODO throw appropriate Exception.
                }
                return "addProduct";
            }

            Product product = new Product();
            product.setTitle(title);
            product.setAuthor(author);
            product.setPrice(Integer.parseInt(price));
            product.setDescription(description);
            product.setDetails(details);
            product.setAboutAuthor(aboutAuthor);


            Product generatedProductId = productDAO.create(product);
            if (generatedProductId != null) {
                req.setAttribute("product", product);

            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "productDetails";
    }
}
