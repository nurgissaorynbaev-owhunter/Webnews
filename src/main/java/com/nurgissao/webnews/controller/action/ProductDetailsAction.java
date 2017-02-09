package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductDAO;
import com.nurgissao.webnews.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailsAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();
            Product product;

            String productId = req.getParameter("productId");
            if (productId != null) {
                product = productDAO.find(Integer.parseInt(productId));
                if (product != null) {
                    req.setAttribute("product", product);

                } else {
                    //TODO throw appropriate Exception
                }
            } else {
                //TODO throw appropriate Exception
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "productDetails";
    }
}
