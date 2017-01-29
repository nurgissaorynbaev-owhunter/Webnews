package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.Product;
import com.nurgissao.webnews.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2ProductDAO implements ProductDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Product find(int id) throws DAOException {
        Connection connection = connectionPool.getConnection();
        Product product;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Product WHERE id = ?")) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            product = map(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Failed to find Product.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return product;
    }

    @Override
    public List<Product> findAll() throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<Product> products = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Product limit 10")) {

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                products.add(map(resultSet));
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to fina all Product.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return products;
    }

    @Override
    public Product create(Product product) throws DAOException {
        Connection connection = connectionPool.getConnection();
        Product tProduct = new Product();

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Product (title, author, price, description, details, aboutAuthor)" +
                        "VALUES (?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, product.getTitle());
            ps.setString(2, product.getAuthor());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getDetails());
            ps.setString(6, product.getAboutAuthor());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                tProduct.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create Product.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return tProduct;
    }

    @Override
    public int update(Product product) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE Product SET title=?, author=?, price=?, description=?," +
                        " details=?, aboutAuthor=? WHERE id=?")) {

            ps.setString(1, product.getTitle());
            ps.setString(2, product.getAuthor());
            ps.setInt(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getDetails());
            ps.setString(6, product.getAboutAuthor());
            ps.setInt(7, product.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to update Product.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    @Override
    public int delete(Product product) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try(PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM Product WHERE id=?")) {

            ps.setInt(1, product.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete Product.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return affectedRowCount;
    }

    private Product map(ResultSet resultSet) throws SQLException {
        Product product = new Product();

        if (resultSet.next()) {
            product.setId(resultSet.getInt(1));
            product.setTitle(resultSet.getString(2));
            product.setAuthor(resultSet.getString(3));
            product.setPrice(resultSet.getInt(4));
            product.setDescription(resultSet.getString(5));
            product.setDetails(resultSet.getString(6));
            product.setAboutAuthor(resultSet.getString(7));
        }
        return product;
    }
}