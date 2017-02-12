package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductDAO;
import com.nurgissao.webnews.model.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductDetailsAction implements Action {
    public static Logger log = Logger.getLogger(ProductDetailsAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();

            String productId = req.getParameter("productId");
            if (productId != null) {
                Product product = productDAO.findById(Integer.parseInt(productId));
                if (product != null) {
                    req.setAttribute("product", product);

                } else {
                    log.info("product is null.");
                }
            } else {
                log.info("product id is null.");
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "productDetails";
    }
}
