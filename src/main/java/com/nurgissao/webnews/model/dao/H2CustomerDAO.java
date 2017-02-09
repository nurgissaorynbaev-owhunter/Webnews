package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2CustomerDAO implements CustomerDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public Customer find(int id) throws DAOException {
        Connection connection = connectionPool.getConnection();
        Customer customer;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Customer WHERE id=?")) {

            ps.setInt(1, id);

            customer = map(ps.executeQuery());

        } catch (SQLException e) {
            throw new DAOException("Failed to find customer by id.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Customer")) {

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setFullName(resultSet.getString(2));
                customer.setCountry(resultSet.getString(3));
                customer.setCity(resultSet.getString(4));
                customer.setHomeAddress(resultSet.getString(5));
                customer.setPhoneNumber(resultSet.getString(6));
                customer.setEmail(resultSet.getString(7));

                customers.add(customer);
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to findAll customers.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return customers;
    }

    @Override
    public Customer create(Customer customer) throws DAOException {
        Connection connection = connectionPool.getConnection();
        Customer tCustomer = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Customer (fullName, country, city, homeAddress, phoneNumber, email)" +
                        " VALUES (?,?,?,?,?,?)")) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getCountry());
            ps.setString(3, customer.getCity());
            ps.setString(4, customer.getHomeAddress());
            ps.setString(5, customer.getPhoneNumber());
            ps.setString(6, customer.getEmail());

            ps.executeUpdate();
            ResultSet getGeneratedKey = ps.getGeneratedKeys();
            if (getGeneratedKey.next()) {
                customer.setId(getGeneratedKey.getInt(1));
                tCustomer = customer;
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create customer.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return tCustomer;
    }

    @Override
    public int update(Customer customer) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE Customer SET fullName=?, country=?, city=?, homeAddress=?," +
                        "phoneNumber=?, email=? WHERE id=?")) {

            ps.setString(1, customer.getFullName());
            ps.setString(2, customer.getCountry());
            ps.setString(3, customer.getCity());
            ps.setString(4, customer.getHomeAddress());
            ps.setString(5, customer.getPhoneNumber());
            ps.setString(6, customer.getEmail());
            ps.setInt(7, customer.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to create customer.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    @Override
    public int delete(Customer customer) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM Customer WHERE id=?")) {

            ps.setInt(1, customer.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete customer.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    private Customer map(ResultSet resultSet) throws SQLException {
        Customer customer = null;
        if (resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt(1));
            customer.setFullName(resultSet.getString(2));
            customer.setCountry(resultSet.getString(3));
            customer.setCity(resultSet.getString(4));
            customer.setHomeAddress(resultSet.getString(5));
            customer.setPhoneNumber(resultSet.getString(6));
            customer.setEmail(resultSet.getString(7));
        }
        return customer;
    }
}
