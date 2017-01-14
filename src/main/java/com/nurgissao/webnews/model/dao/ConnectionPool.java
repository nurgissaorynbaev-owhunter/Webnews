package com.nurgissao.webnews.model.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    public static Logger log = Logger.getLogger(ConnectionPool.class);
    private static final int MAX_POOL_SIZE = 30;
    private static final int MIN_POOL_SIZE = 10;
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_SPECIFIC_KEY = "h2.jdbc";
    private volatile static ConnectionPool instance;
    private static BlockingQueue<Connection> pool;
    private static DAOProperties properties;
    private static boolean isOver = false;

    private ConnectionPool() {
        initialize();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private void initialize() {
        pool = new ArrayBlockingQueue<>(MAX_POOL_SIZE);
        properties = new DAOProperties(PROPERTY_SPECIFIC_KEY);
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        for (int i = 0; i < MIN_POOL_SIZE; i++) {
            pool.add(createConnection());
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        if (pool.isEmpty() && (!isOver)) {
            for (int i = 0; i < 20; i++) {
                pool.add(createConnection());
            }
            isOver = true;
        }
        try {
            connection = pool.take();

        } catch (InterruptedException e) {
            log.error("Failed to take connection from pool.", e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            pool.put(connection);

        } catch (InterruptedException e) {
            log.error("Failed to put connection to pool.", e);
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        String url = properties.getValue(PROPERTY_URL);
        String driver = properties.getValue(PROPERTY_DRIVER);
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);

        } catch (ClassNotFoundException | SQLException e) {
            log.error("Failed to connect to database.", e);
        }
        return connection;
    }

    public void shutdownConnections() {
        for (Connection con : pool) {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Failed to shutdown connections.", e);
            }
        }
    }
}
