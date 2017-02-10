package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2ShoppingCartDAO implements ShoppingCartDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public ShoppingCart find(String cookieId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ShoppingCart shoppingCart = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM ShoppingCart WHERE cookieId=?")) {

            ps.setString(1, cookieId);

            ResultSet resultSet = ps.executeQuery();
            shoppingCart = map(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Failed to find shopping cart item by jsessionid", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return shoppingCart;
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
            throw new DAOException("Failed to find ShoppingCart item.", e);
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
                "INSERT INTO ShoppingCart (productId, cookieId, quantity, guestCustomerId) VALUES (?,?,?,?)")) {

            ps.setInt(1, shoppingCart.getProductId());
            ps.setString(2, shoppingCart.getCookieId());
            ps.setInt(3, shoppingCart.getQuantity());
            ps.setInt(4, shoppingCart.getGuestCustomerId());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                shoppingCart.setId(resultSet.getInt(1));
                tShoppingCart = shoppingCart;
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create shopping cart item.", e);
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
                "UPDATE ShoppingCart SET quantity=?, guestCustomerId=? WHERE productId=?")) {

            ps.setInt(1, shoppingCart.getQuantity());
            ps.setInt(2, shoppingCart.getProductId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete shopping cart item.", e);
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

            ps.setInt(1, shoppingCart.getProductId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete shopping cart item.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return affectedRowCount;
    }

    @Override
    public int delete(int productId, String cookieId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM ShoppingCart WHERE cookieId=? AND productId=?")) {

            ps.setString(1, cookieId);
            ps.setInt(2, productId);

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete shopping cart item.", e);
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
            shoppingCart.setGuestCustomerId(resultSet.getInt(5));
        }
        return shoppingCart;
    }

}
