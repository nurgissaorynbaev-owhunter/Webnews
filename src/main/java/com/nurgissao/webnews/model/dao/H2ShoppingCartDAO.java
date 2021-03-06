package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2ShoppingCartDAO implements ShoppingCartDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public List<ShoppingCart> findAllByCookieId(String cookieId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<ShoppingCart> shoppingCarts = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM ShoppingCart WHERE cookieId=?")) {

            ps.setString(1, cookieId);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setId(resultSet.getInt(1));
                shoppingCart.setProductId(resultSet.getInt(2));
                shoppingCart.setCookieId(resultSet.getString(3));
                shoppingCart.setQuantity(resultSet.getInt(4));

                shoppingCarts.add(shoppingCart);
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to findAllByCustomerId Shopping cart item by cookieId.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return shoppingCarts;
    }

    @Override
    public ShoppingCart find(int productId, String cookieId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ShoppingCart shoppingCart = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM ShoppingCart WHERE productId=? and cookieId=?")) {

            ps.setInt(1, productId);
            ps.setString(2, cookieId);

            ResultSet resultSet = ps.executeQuery();
            shoppingCart = map(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Failed to findById ShoppingCart item.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return shoppingCart;
    }

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ShoppingCart tShoppingCart = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO ShoppingCart (productId, cookieId, quantity) VALUES (?,?,?)")) {

            ps.setInt(1, shoppingCart.getProductId());
            ps.setString(2, shoppingCart.getCookieId());
            ps.setInt(3, shoppingCart.getQuantity());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getInt(1));
                tShoppingCart = shoppingCart;
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create Shopping cart item.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return tShoppingCart;
    }

    @Override
    public int update(ShoppingCart shoppingCart) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE ShoppingCart SET quantity=? WHERE productId=?")) {

            ps.setInt(1, shoppingCart.getQuantity());
            ps.setInt(2, shoppingCart.getProductId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to update Shopping cart item.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return affectedRowCount;
    }

    @Override
    public int delete(ShoppingCart shoppingCart) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM ShoppingCart WHERE id=?")) {

            ps.setInt(1, shoppingCart.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete Shopping cart item.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return affectedRowCount;
    }

    private ShoppingCart map(ResultSet resultSet) throws SQLException {
        ShoppingCart shoppingCart = null;
        if (resultSet.next()) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setId(resultSet.getInt(1));
            shoppingCart.setProductId(resultSet.getInt(2));
            shoppingCart.setCookieId(resultSet.getString(3));
            shoppingCart.setQuantity(resultSet.getInt(4));
        }
        return shoppingCart;
    }

}
