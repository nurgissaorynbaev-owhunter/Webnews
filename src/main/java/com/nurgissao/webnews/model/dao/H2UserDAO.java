package com.nurgissao.webnews.model.dao;

import com.nurgissao.webnews.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2UserDAO implements UserDAO {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public User find(int id) throws DAOException {
        Connection connection = connectionPool.getConnection();
        User user;

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM USER WHERE id=?")) {

            ps.setInt(1, id);
            user = map(ps.executeQuery());

        } catch (SQLException e) {
            throw new DAOException("Failed to find a User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return user;
    }

    @Override
    public User find(String email, String password) throws DAOException {
        Connection connection = connectionPool.getConnection();
        User user;
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM USER WHERE email=? AND password=?")) {

            ps.setString(1, email);
            ps.setString(2, password);

            user = map(ps.executeQuery());

        } catch (SQLException e) {
            throw new DAOException("Failed to find User by email, password.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {
        Connection connection = connectionPool.getConnection();
        List<User> users = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM USER")) {

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setRole(resultSet.getString(6));
                user.setStatus(resultSet.getString(7));
                user.setCustomerId(resultSet.getInt(8));

                users.add(user);
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to findAll Users.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return users;
    }

    @Override
    public User create(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        User tUser = null;

        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO USER (firstName, lastName, email, password, role, status, customerId)" +
                        "VALUES (?,?,?,?,?,?,?)")) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getStatus());
            ps.setInt(7, user.getCustomerId());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                tUser = user;
            }

        } catch (SQLException e) {
            throw new DAOException("Failed to create User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return tUser;
    }

    @Override
    public int update(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE USER SET firstName=?, lastName=?, email=?, password=?, role=?, status=?," +
                        "customerId=? WHERE id=?")) {

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());
            ps.setString(6, user.getStatus());
            ps.setInt(7, user.getCustomerId());
            ps.setInt(8, user.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to update User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    @Override
    public int delete(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM USER WHERE id=?")) {

            ps.setInt(1, user.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to delete User.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    @Override
    public int changePassword(User user) throws DAOException {
        Connection connection = connectionPool.getConnection();
        int affectedRowCount;

        try (PreparedStatement ps = connection.prepareStatement(
                "UPDATE USER SET password=? WHERE id=?")) {

            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getId());

            affectedRowCount = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to change password.", e);
        } finally {
            connectionPool.closeConnection(connection);
        }
        return affectedRowCount;
    }

    private User map(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setRole(resultSet.getString(6));
            user.setStatus(resultSet.getString(7));
            user.setCustomerId(resultSet.getInt(8));
        }
        return user;
    }
}
