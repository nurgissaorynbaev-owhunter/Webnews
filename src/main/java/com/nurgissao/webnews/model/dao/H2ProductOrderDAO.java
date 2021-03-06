package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.ProductOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2ProductOrderDAO implements ProductOrderDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public ProductOrder findById(int id) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ProductOrder tProductOrder = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM ProductOrder WHERE id=?")) {

            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            tProductOrder = map(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Failed to findById Product order by id.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return tProductOrder;
    }

    @Override
    public List<ProductOrder> findAllByCustomerId(int customerId) throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<ProductOrder> productOrders = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM ProductOrder WHERE customerId=?")) {

            ps.setInt(1, customerId);

            ResultSet resultSet = ps.executeQuery();
            productOrders = mapList(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Failed to findById all Product orders by customerId.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return productOrders;
    }

    @Override
    public List<ProductOrder> findAll() throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<ProductOrder> productOrders = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM ProductOrder")) {

            ResultSet resultSet = ps.executeQuery();
            productOrders = mapList(resultSet);

        } catch (SQLException e) {
            throw new DAOException("Failed to findById all by Product orders.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return productOrders;
    }

    @Override
    public ProductOrder create(ProductOrder productOrder) throws DAOException {
        Connection connection = connectionPool.getConnection();
        ProductOrder tProductOrder = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO ProductOrder (customerId, productId, productQuantity) VALUES (?,?,?)")) {

            ps.setInt(1, productOrder.getCustomerId());
            ps.setInt(2, productOrder.getProductId());
            ps.setInt(3, productOrder.getProductQuantity());

            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                productOrder.setId(resultSet.getInt(1));
                tProductOrder = productOrder;
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create productOrder.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return tProductOrder;
    }

    @Override
    public int update(ProductOrder productOrder) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE ProductOrder SET customerId=?, productId=?, productQuantity=? WHERE id=?")) {

            ps.setInt(1, productOrder.getCustomerId());
            ps.setInt(2, productOrder.getProductId());
            ps.setInt(3, productOrder.getProductQuantity());
            ps.setInt(4, productOrder.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to update Product order.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return affectedRowCount;
    }

    @Override
    public int delete(ProductOrder productOrder) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM ProductOrder WHERE id=?")) {

            ps.setInt(1, productOrder.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete Product order.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }

        return affectedRowCount;
    }

    private List<ProductOrder> mapList(ResultSet resultSet) throws SQLException {
        List<ProductOrder> productOrders = new ArrayList<>();
        while (resultSet.next()) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setId(resultSet.getInt(1));
            productOrder.setCustomerId(resultSet.getInt(2));
            productOrder.setProductId(resultSet.getInt(3));
            productOrder.setProductQuantity(resultSet.getInt(4));

            productOrders.add(productOrder);
        }
        return productOrders;
    }

    private ProductOrder map(ResultSet resultSet) throws SQLException {
        ProductOrder productOrder = null;
        if (resultSet.next()) {
            productOrder = new ProductOrder();
            productOrder.setId(resultSet.getInt(1));
            productOrder.setCustomerId(resultSet.getInt(2));
            productOrder.setProductId(resultSet.getInt(3));
            productOrder.setProductQuantity(resultSet.getInt(4));
        }
        return productOrder;
    }
}
