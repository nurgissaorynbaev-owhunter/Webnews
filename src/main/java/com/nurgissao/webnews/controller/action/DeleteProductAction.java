package com.nurgissao.webnews.controller.action;

import com.nurgissao.webnews.model.dao.DAOException;
import com.nurgissao.webnews.model.dao.DAOFactory;
import com.nurgissao.webnews.model.dao.DataSourceType;
import com.nurgissao.webnews.model.dao.ProductDAO;
import com.nurgissao.webnews.model.entity.Product;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductAction implements Action {
    public static Logger log = Logger.getLogger(DeleteProductAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try {
            DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
            ProductDAO productDAO = daoFactory.getProductDAO();

            String productId = req.getParameter("productId");
            if (productId != null) {
                Product product = new Product();
                product.setId(Integer.parseInt(productId));

                int affectedRowCount = productDAO.delete(product);
                if (affectedRowCount == 0) {
                    log.info("Product not deleted.");
                }

            } else {
                log.info("product id is null.");
            }

        } catch (DAOException e) {
            throw new ActionException(e);
        }

        return "home";
    }
}
