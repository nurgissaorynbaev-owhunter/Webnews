package com.nurgissao.webnews;


import com.nurgissao.webnews.model.dao.*;
import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.User;

import java.util.LinkedList;
import java.util.List;

public class App {

    public static void main(String[] args) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(DataSourceType.H2);
        ProductDAO productDAO = daoFactory.getProductDAO();

        Product product = new Product();
//        product.setTitle("Java Guide");
//        product.setAuthor("Horstman");
//        product.setPrice(145);
//        product.setDescription("111");
//        product.setDetails("222");
//        product.setAboutAuthor("333");
        product.setId(1);

        System.out.println(productDAO.delete(product));

    }
}
