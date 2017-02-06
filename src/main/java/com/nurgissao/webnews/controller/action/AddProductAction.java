package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductDAO;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
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
            String image = getFileName(req);

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
                for (Map.Entry<String, String> entry : violations.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());

                    //TODO throw appropriate Exception.
                }
            }

            Product product = new Product();
            product.setTitle(title);
            product.setAuthor(author);
            product.setPrice(Integer.parseInt(price));
            product.setDescription(description);
            product.setDetails(details);
            product.setAboutAuthor(aboutAuthor);
            product.setImage(image);

            String submit = req.getParameter("submit");
            if (submit != null) {
                Product uProduct = new Product();
                HttpSession session = req.getSession();
                Product sProduct = (Product) session.getAttribute("product");


                uProduct.setId(sProduct.getId());
                uProduct.setTitle(title);
                uProduct.setAuthor(author);
                uProduct.setPrice(Integer.parseInt(price));
                uProduct.setDescription(description);
                uProduct.setDetails(details);
                uProduct.setAboutAuthor(aboutAuthor);
                uProduct.setImage(image);

                System.out.println(uProduct);

                int affectedRowCount = productDAO.update(uProduct);
                if (affectedRowCount == 0) {
                    //TODO throw appropriate Exception.
                }
                session.removeAttribute("product");

                return "home";

            } else {
                productDAO.create(product);
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }
        return "showAddProduct";
    }

    private String getFileName(HttpServletRequest req) {
        String fileName = null;
        try {
            Part part = req.getPart("fileUpload");
            String contentDisposition = part.getHeader("content-disposition");

            String[] tokens = contentDisposition.split(";");
            for (String token : tokens) {
                if (token.trim().startsWith("filename")) {
                    fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);
                }
            }

        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        return fileName;
    }

}
