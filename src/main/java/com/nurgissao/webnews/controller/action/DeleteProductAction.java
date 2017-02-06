package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductDAO;
import com.nurgissao.webnews.model.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();

            String productId = req.getParameter("productId");
            Product product;
            if (productId != null) {
                product = new Product();
                product.setId(Integer.parseInt(productId));

                int affectedRowCount = productDAO.delete(product);
                if (affectedRowCount == 0) {
                    //TODO throw appropriate Exception
                }

            } else {
                //TODO throw appropriate Exception
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
